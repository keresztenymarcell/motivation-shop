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
                <a class="btn btn-success" href="#">Add to cart</a>
            </div>
        </div>
    </div>
    </div>`
    },

    clearProducts() {
        document.getElementById("products").textContent = "";
    },

}
main.init();