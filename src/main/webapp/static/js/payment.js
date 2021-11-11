const pay = {
    async init() {
        await pay.fetchFromOrderApi();
        const confirmButton = document.getElementById("confirm");
        confirmButton.addEventListener("click", pay.paymentHandler);

    },

    async paymentHandler(){
        const payPalDiv = document.getElementById("paypal-button");
        const payPalFormOpen = payPalDiv.getAttribute("aria-expanded");
        const creditDiv = document.getElementById("credit-card-button");
        const creditFormOpen = creditDiv.getAttribute("aria-expanded");
        if(payPalFormOpen === "true" || creditFormOpen === "true"){
            if(payPalFormOpen === "true") {
                const payPalUser = document.getElementById('paypal-user').value;
                const url = `/payment/paypal?paypal-user=${payPalUser}`;
                const json = await pay.fetchFromApi(url)
                if (json.isSuccessPayment == true) {
                    window.location.href = "http://localhost:8080/payment";
                } else {
                    alert("The paypal payment was not successful!");
                }

            }
            else{
                const CVV = document.getElementById('cvv').value;
                const url = `/payment/credit?cvv=${CVV}`;
                const json = await pay.fetchFromApi(url)
                if (json.isSuccessPayment == true){
                    window.location.href = "http://localhost:8080/payment";
                } else {
                    alert("The credit payment was not successful!");
                }
            }
        }
    },

    async fetchFromApi(url) {
        const response = await fetch(url);
        return response.json();
    },

    addTotalPrice(productsInCart) {
        let cartValue = pay.getTotalPrice(productsInCart);
        const payCard = document.getElementById("paymentCard");
        payCard.textContent = "";
        payCard.innerHTML += pay.paymentSide() + pay.fillSummarySide(cartValue);

    },

    fillSummarySide(cartValue){
        return `<div class="col-md-6"> <span>Summary</span>
                                <div class="card">
                                    <hr class="mt-0 line">
                                    <div class="p-3">
                                        <div class="d-flex justify-content-between mb-2"> <span>Total Price</span> <span id="totalPrice" >${cartValue} USD</span> </div>
                                    </div>
                                    <hr class="mt-0 line">
                                    <div class="p-3"> <button id="confirm" class="btn btn-primary btn-block free-button">Continue confirmation</button>
                                        <div class="text-center"> <a href="#">Reset my payment and go back</a> </div>
                                    </div>
                                </div>
                            </div>`

    },

    getTotalPrice(productsInCart){
        let totalPrice = 0;
        productsInCart.forEach(product => {
            totalPrice += product.itemTotal;
        })
        return totalPrice;
    },

    paymentSide(){
        return `<div class="row g-3">
        <div class="col-md-6"> <span>Payment Method</span>
            <div class="card">
                <div class="accordion" id="accordionExample">
                    <div class="card">
                        <div class="card-header p-0" id="headingTwo">
                            <h2 class="mb-0"> <button id="paypal-button" class="btn btn-light btn-block text-left collapsed p-3 rounded-0 border-bottom-custom" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                <div class="d-flex align-items-center justify-content-between"> <span>Paypal</span> <img src="https://i.imgur.com/7kQEsHU.png" width="30"> </div>
                            </button> </h2>
                        </div>
                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                            <div class="card-body"> <input id="paypal-user" type="text" class="form-control" placeholder="Paypal Username"> </div>
                            <div class="card-body"> <input type="text" class="form-control" placeholder="Paypal password"> </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header p-0">
                            <h2 class="mb-0"> <button id="credit-card-button" class="btn btn-light btn-block text-left p-3 rounded-0" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                <div class="d-flex align-items-center justify-content-between"> <span>Credit card</span>
                                    <div class="icons"> <img src="https://i.imgur.com/2ISgYja.png" width="30"> <img src="https://i.imgur.com/W1vtnOV.png" width="30"> <img src="https://i.imgur.com/35tC99g.png" width="30"> <img src="https://i.imgur.com/2ISgYja.png" width="30"> </div>
                                </div>
                            </button> </h2>
                        </div>
                        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
                            <div class="card-body payment-card-body"> <span class="font-weight-normal card-text">Card Number</span>
                                <div class="input"> <i class="fa fa-credit-card"></i> <input type="text" class="form-control" placeholder="0000 0000 0000 0000"> </div>
                                <div class="row mt-3 mb-3">
                                    <div class="col-md-6"> <span class="font-weight-normal card-text">Expiry Date</span>
                                        <div class="input"> <i class="fa fa-calendar"></i> <input type="text" class="form-control" placeholder="MM/YY"> </div>
                                    </div>
                                    <div class="col-md-6"> <span class="font-weight-normal card-text">CVC/CVV</span>
                                        <div class="input"> <i class="fa fa-lock"></i> <input id="cvv" type="text" class="form-control" placeholder="000"> </div>
                                    </div>
                                </div> <span class="text-muted certificate-text"><i class="fa fa-lock"></i> Your transaction is secured with ssl certificate</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>`
    },

    async fetchFromOrderApi(){
        const url = `/api/order`;
        const order = await pay.fetchFromApi(url);
        pay.addTotalPrice(order.cart);
    },

    async fetchFromApi(url) {
        const response = await fetch(url);
        return response.json();
    },
}
pay.init();


