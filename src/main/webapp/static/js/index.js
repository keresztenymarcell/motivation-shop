const main = {
    init() {
        const categories = document.getElementById("categories");
        categories.addEventListener("mouseup", async (event) => {
            await main.refreshProductsWithFetchedProducts("category", event.target.value);
        })

        const suppliers = document.getElementById("suppliers");
        suppliers.addEventListener("mouseup", async (event) => {
            await main.refreshProductsWithFetchedProducts("supplier", event.target.value);
        })

        this.loadAddToCartButtonsWithEventListeners();

    },

    loadAddToCartButtonsWithEventListeners() {
        const cardButtons = document.querySelectorAll(".add-to-cart");
        cardButtons.forEach(button => {
            button.addEventListener("click", async (e) => {
                if(button.getAttribute("data-cart") != null){
                    const url = `/api/order`;
                    const cart = await main.fetchFromApi(url);
                    main.fillShoppingCart(cart);

                }
                else {
                    const productId = e.target.dataset.productId;
                    const url = `/api/add-to-cart?id=${productId}`
                    const order = await main.fetchFromApi(url);
                    main.increaseCartContent(order);
                    main.increaseCartValue(order);
                }
            })
        })

    },


    async refreshProductsWithFetchedProducts(title, id) {
        main.clearMainContainerToOneitem();
        const products = await main.fetchFromApi(`/api/filter?name=${title}&id=${id}`);
        let currentTitle;
        console.log(products)
        if (title === "category") {
            currentTitle = products[0].productCategory.name;
        } else if (title === "supplier") {
            currentTitle = products[0].supplier.name;
        }
        main.setCardTitle(currentTitle);
        main.clearProducts();
        main.fillProductsDivWithProducts(products);
        main.loadAddToCartButtonsWithEventListeners()
    },

    async fetchFromApi(url) {
        const response = await fetch(url);
        return response.json();
    },

    clearMainContainerToOneitem() {
        const mainContainer = document.getElementById("main-container");
        while (mainContainer.childElementCount > 1) {
            mainContainer.removeChild(mainContainer.lastChild);
        }

    },

    setCardTitle(title) {
        const cardTitle = document.getElementById("category-title");
        cardTitle.textContent = title;
    },

    fillProductsDivWithProducts(products) {
        const productsDiv = document.getElementById("products");
        for (let i = 0; i < products.length; i++) {
            productsDiv.innerHTML += this.createCardFromProduct(products[i]);
        }
    },

    createCardFromProduct(product) {
        return `<div class="col col-sm-12 col-md-6 col-lg-4">
                    <div class="card">
                        <img class="" src=/static/img/product_${product.id}.jpg alt="" />
                        <div class="card-header">
                            <h4 class="card-title">${product.name}</h4>
                            <p class="card-text">${product.description}</p>
                        </div>
                        <div class="card-body">
                            <div class="card-text">
                                <p class="lead">${product.defaultPrice} ${product.defaultCurrency}</p>
                            </div>
                            <div class="card-text">
                                <a class="btn btn-success add-to-cart" data-product-id="${product.id}" href="#">Add to cart</a>
                            </div>
                        </div>
                    </div>
            </div>`
    },

    clearProducts() {
        document.getElementById("products").textContent = "";
    },

    fillShoppingCart(productsInCart){
        const dropDownMenu = document.getElementById("shopping-review");
        dropDownMenu.textContent = "";
        dropDownMenu.innerHTML += main.createReviewHeaderAndButton(productsInCart);
        main.loadProduct(productsInCart, dropDownMenu);
        dropDownMenu.innerHTML += main.addCheckOutButton();
    },

    createReviewHeaderAndButton(productsInCart) {
        let cartValue = main.getTotalPrice(productsInCart);
        let numberOfProducts = main.getProductsNumber(productsInCart);

        return `<div class="row total-header-section">
                    <div class="col-lg-6 col-sm-6 col-6">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                        <span class="badge badge-pill badge-danger">${numberOfProducts}</span>
                    </div>
                    <div class="col-lg-6 col-sm-6 col-6 total-section text-right">
                        <p>Total price: <span id="total-value" class="text-info">${cartValue} USD</span></p>
                    </div>
                </div>`
    },

    loadProduct(productsInCart, menu){
        productsInCart.forEach(lineItem => {
            menu.innerHTML += main.fill(lineItem);
        })

    },

    fill(product){
        return `<div class="row cart-detail">
                    <div class="col-lg-4 col-sm-4 col-4 cart-detail-img">
                        <img class="cart-img" src=/static/img/product_${product.productId}.jpg alt="" />
                    </div>
                    <div class="col-lg-8 col-sm-8 col-8 cart-detail-product">
                        <p>${product.name}</p>
                        <span class="price text-info"> ${product.itemTotal}  ${product.currency}</span> <span class="count"> Quantity:${product.quantity}</span>
                    </div>
                </div>`
    },

    addCheckOutButton(){
        return `<div class="row">
                    <div class="col-lg-12 col-sm-12 col-12 text-center checkout">
                        <button class="btn btn-primary btn-block">Checkout</button>
                    </div>
                </div>`
    },


    increaseCartContent(cart){
        const cartContains = document.getElementById("shop-contains");
        cartContains.textContent = cart.totalItems;

    },
    increaseCartValue(number){
        const cartValue = document.getElementById("shop-value");
        cartValue.textContent = number.orderTotalValue +" "+ "USD";
    },

    getTotalPrice(productsInCart){
        let totalPrice = 0;
        productsInCart.forEach(product => {
            totalPrice += product.itemTotal;
        })
        return totalPrice;
    },

    getProductsNumber(productsInCart){
        let products = 0;
        productsInCart.forEach(product => {
            products += product.quantity;
        })
        return products;

    },

}
main.init();