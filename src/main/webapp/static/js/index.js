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

        const cardButtons = document.querySelectorAll(".add-to-cart");

        cardButtons.forEach(button => {
            button.addEventListener("click", async (e) => {
                const productId = e.target.dataset.productId;
                const url = `/api/add-to-cart?id=${productId}`
                const number = await main.fetchFromApi(url);
                main.increaseCartContent(number);
                main.increaseCartValue(number);

            })
        })

        const cartIcon = document.getElementById("cart-icon");
        cartIcon.addEventListener("click", async () =>{
            console.log("itt vagyok!")
            const url = `/api/order`;
            const responseOrder = await main.fetchFromApi(url);
            main.fillShoppingCart(responseOrder);
        })
    },


    async refreshProductsWithFetchedProducts(title, id) {
        const products = await main.fetchFromApi(`/api/filter?name=${title}&id=${id}`);
        main.clearProducts();
        main.fillProductsDivWithProducts(products);
    },

    async fetchFromApi(url) {
        const response = await fetch(url);
        return response.json();
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

    fillShoppingCart(orderList){
        document.getElementById("shopping-cart-dropdown").textContent = "";
        this.createListToCart(orderList);
    },

    createListToCart(orderList){
        orderList.forEach(lineItem => {
            document.getElementById("shopping-cart-dropdown").innerHTML += main.fillLineItemsToCart(lineItem);
        })
    },

    fillLineItemsToCart(lineItem){
        return `<li>
                      <span class="item">
                        <span class="item-left">
                            <img  class="cart-img" src=/static/img/product_${lineItem.productId}.jpg alt="" />
                            <span class="item-info">
                                <span>${lineItem.name}</span>
                                <span>${lineItem.quantity}</span>
                                <span>${lineItem.itemTotal}</span>
                            </span>
                        </span>
                    </span>
                </li>`
    },
    increaseCartContent(number){
        const cartContains = document.getElementById("shop-contains");
        cartContains.textContent = number.totalItems;
    },
    increaseCartValue(number){
        const cartContains = document.getElementById("shop-value");
        cartContains.textContent = number.totalValue +" "+ number.currencyString;
    },



}
main.init();