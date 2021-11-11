const pay = {
    init() {
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
                }
                alert("The paypal payment was not successfull!");

            }
            else{
                const CVV = document.getElementById('cvv').value;
                const url = `/payment/credit?cvv=${CVV}`;
                const json = await pay.fetchFromApi(url)
                if (json.isSuccessPayment == true){
                    window.location.href = "http://localhost:8080/payment";
                }
                alert("The credit payment was not successfull!");
            }
        }
    },

    async fetchFromApi(url) {
        const response = await fetch(url);
        return response.json();
    },

    loaderAnimation(){

    },
}
pay.init();


