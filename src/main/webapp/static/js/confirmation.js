const conf = {
    async init() {
        const order = await conf.fetchFromApi(`/api/order`);
        await conf.fillConfirmationPage(order);

    },

    async fetchFromApi(url) {
        const response = await fetch(url);
        return response.json();
    },


    async fillConfirmationPage(order) {
        console.log(order)
        console.log(order.cart)
        const confirm = document.getElementById("confirm");
        confirm.textContent = "";
        confirm.innerHTML += await conf.fillHeader(order);
        confirm.innerHTML += conf.fillAllProduct(order.cart) + conf.fullSubTotal(order) + conf.fillFooter(order);


    },

    fillAllProduct(cart){
        const productsDiv = document.getElementById("products");
        cart.forEach(product => {
            productsDiv.innerHTML += conf.fillProduct(product);
        })

    },

    async fillHeader(cartValue){
        return `<div class="row d-flex justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="text-left logo p-2 px-5"> <img src="" width="50"> </div>
                <div class="invoice p-5">
                    <h5>Your order Confirmed!</h5> <span class="font-weight-bold d-block mt-4">Hello, Chris</span> <span>You order has been confirmed and will be shipped in next two days!</span>
                    <div class="payment border-top mt-3 mb-3 border-bottom table-responsive">
                        <table class="table table-borderless">
                            <tbody>
                            <tr>
                                <td>
                                    <div class="py-2"> <span class="d-block text-muted">Order Date</span> <span>12 Jan,2018</span> </div>
                                </td>
                                <td>
                                    <div class="py-2"> <span class="d-block text-muted">Order No</span> <span>MT12332345</span> </div>
                                </td>
                                <td>
                                    <div class="py-2"> <span class="d-block text-muted">Payment</span> <span><img src="https://img.icons8.com/color/48/000000/mastercard.png" width="20" /></span> </div>
                                </td>
                                <td>
                                    <div class="py-2"> <span class="d-block text-muted">Shiping Address</span> <span>414 Advert Avenue, NY,USA</span> </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="product border-bottom table-responsive">
                        <table class="table table-borderless">
                            <tbody id="products">`

    },

    getTotalPrice(productsInCart){
        let totalPrice = 0;
        productsInCart.forEach(product => {
            totalPrice += product.itemTotal;
        })
        return totalPrice;
    },

    fillProduct(product){
        return `
                <tr>
                    <td width="20%"> <img id="conf-page" class="" src=/static/img/product_${product.productId}.jpg alt="" /> </td>
                    <td width="60%"> <span class="font-weight-bold">${product.name}</span>
                        <div class="product-qty"> <span class="d-block">Quantity:${product.quantity}</span> </div>
                    </td>
                    <td width="20%">
                        <div class="text-right"> <span class="font-weight-bold">${product.itemTotal}</span> </div>
                    </td>
                </tr>
                            `
    },

    fullSubTotal(productsInCart){
        const totalPrice = conf.getTotalPrice(productsInCart);
        return `</tbody>
                        </table>
                    </div>
                <div class="row d-flex justify-content-end">
                        <div class="col-md-5">
                            <table class="table table-borderless">
                                <tbody class="totals">
                                <tr class="border-top border-bottom">
                                    <td>
                                        <div class="text-left"> <span class="font-weight-bold">Subtotal</span> </div>
                                    </td>
                                    <td>
                                        <div class="text-right"> <span class="font-weight-bold">${totalPrice}</span> </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>`
    },

    fillFooter(productsInCart){
        return `<p>We will be sending shipping confirmation email when the item shipped successfully!</p>
                    <p class="font-weight-bold mb-0">Thanks for shopping with us!</p> <span>Motivation Team</span>
                </div>
                <div class="d-flex justify-content-between footer p-3"> <span>Need Help? visit our <a href="#"> help center</a></span> <span>12 Nov, 2021</span> </div>
            </div>
        </div>
    </div>`
    },

}
conf.init();


