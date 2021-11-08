const main = {
    init() {
        const categories = document.getElementById("categories");
        categories.addEventListener("mouseup", async (event) => {
            const categoryId = event.target.value;
            const products = await main.fetchFromApi(`/api/filter?name=category&id=${categoryId}`);
            main.clearProducts();
            main.fillProductsWithProducts(products);
        })

        const suppliers = document.getElementById("suppliers");
        suppliers.addEventListener("mouseup", async (event) => {
            const supplierId = event.target.value;
            const products = await main.fetchFromApi(`/api/filter?name=supplier&id=${supplierId}`);
            main.clearProducts();
            main.fillProductsWithProducts(products);
        })
    },
    async fetchFromApi(url) {
        const response = await fetch(url);
        return response.json();
    },

    fillProductsWithProducts(products) {
        const productsDiv = document.getElementById("products");

        for (let i = 0; i < products.length; i++) {
            console.log(products[i]);
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