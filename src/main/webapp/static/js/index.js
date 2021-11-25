const main = {
    async init() {
        const categories = document.getElementById("categories");
        categories.addEventListener("mouseup", async (event) => {
            await main.refreshProductsWithFetchedProducts("category", event.target.value);
        })

        const suppliers = document.getElementById("suppliers");
        suppliers.addEventListener("mouseup", async (event) => {
            await main.refreshProductsWithFetchedProducts("supplier", event.target.value);
        })

        //await this.refreshCartIcon();
        this.loadAddToCartButtonsWithEventListeners();

    },

    loadAddToCartButtonsWithEventListeners() {
        const cardButtons = document.querySelectorAll(".add-to-cart");
        cardButtons.forEach(button => {
            button.addEventListener("click", async (e) => {
                console.log("gomb")
                if(button.getAttribute("data-cart") != null){
                    const url = `/api/order`;
                    const cart = await main.fetchFromApi(url);
                    console.log(cart)
                    main.fillShoppingCart(cart);

                }
                else {
                    console.log("gomb");
                    const productId = e.target.dataset.productId;
                    const url = `/api/cart?id=${productId}`
                    await main.fetchFromApi(url);
                    await main.refreshCartIcon();
                }
            })
        })

    },

    async refreshCartIcon() {
        const url = `/api/order`
        const cart = await main.fetchFromApi(url);
        console.log(cart);
        if (cart.lineItems.length > 0) {
            main.increaseCartContent(cart);
            main.increaseCartValue(cart);
        } else {
            main.setUpEmptyCartIcon()
        }
    },

    setUpEmptyCartIcon() {
        const cartContains = document.getElementById("shop-contains");
        cartContains.textContent = "";
        const cartValue = document.getElementById("shop-value");
        cartValue.textContent = "";

    },

    async refreshProductsWithFetchedProducts(title, id) {
        main.clearMainContainerToOneitem();
        const products = await main.fetchFromApi(`/api/filter?name=${title}&id=${id}`);
        let currentTitle;
        if (title === "category") {
            currentTitle = products[0].productCategory.name;
        } else if (title === "supplier") {
            currentTitle = products[0].supplier.name;
        }
        main.setCardTitle(currentTitle);
        main.clearProducts();
        main.fillProductsDivWithProducts(products);
        main.loadAddToCartButtonsWithEventListeners();
    },

    async fetchFromApi(url) {
        const response = await fetch(url);
        console.log(response)
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

    fillShoppingCart(cart){
        const dropDownMenu = document.getElementById("shopping-review");
        dropDownMenu.textContent = "";
        dropDownMenu.innerHTML += main.createReviewHeaderAndButton(cart);
        main.loadProduct(cart, dropDownMenu);
        dropDownMenu.innerHTML += main.addCheckOutButton();
    },

    createReviewHeaderAndButton(cart) {
        let cartValue = cart.totalPrice;
        let numberOfProducts = cart.totalItems;

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

    loadProduct(cart, menu){
        cart.lineItems.forEach(lineItem => {
            menu.innerHTML += main.fill(lineItem);
        })

    },

    fill(lineItem){
        return `<div class="row cart-detail">
                    <div class="col-lg-4 col-sm-4 col-4 cart-detail-img">
                        <img class="cart-img" src=/static/img/product_${lineItem.product.id}.jpg alt="" />
                    </div>
                    <div class="col-lg-8 col-sm-8 col-8 cart-detail-lineItem">
                        <p>${lineItem.name}</p>
                        <span class="price text-info"> ${lineItem.product.defaultPrice * lineItem.quantity}  ${lineItem.product.defaultCurrency}</span> <span class="count"> Quantity:${lineItem.quantity}</span>
                    </div>
                </div>`
    },

    addCheckOutButton(){
        return `<div class="row">
                    <div class="col-lg-12 col-sm-12 col-12 text-center checkout">
                        <a href="/shopping-cart"><button class="btn btn-primary btn-block">Edit Cart</button></a>
                    </div>
                </div>`
    },


    increaseCartContent(order){
            const cartContains = document.getElementById("shop-contains");
            cartContains.textContent = order.totalItems;
    },
    increaseCartValue(order){
        const cartValue = document.getElementById("shop-value");
        cartValue.textContent = order.totalPrice +" "+ "USD";
    },

    /*getTotalPrice(cart){
        let totalPrice = 0;
        cart.lineItems.forEach(product => {
            totalPrice += product.product.defaultPrice * product.quantity;
        })
        return totalPrice;
    },*/

    getProductsNumber(productsInCart){
        let products = 0;
        productsInCart.forEach(product => {
            products += product.quantity;
        })
        return products;

    },

}
main.init();