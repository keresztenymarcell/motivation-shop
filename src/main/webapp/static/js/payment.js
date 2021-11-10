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
                const url = `/payment/paypal?user=${payPalUser}`;
                const json = await pay.fetchFromApi(url)
                console.log(json);
                if (json.isSuccessPayment == true) {
                    const paymentCheckUrl = `/payment`;
                    await pay.fetchFromApi(paymentCheckUrl);
                }
            }
            else{
                const CVV = document.getElementById('cvv').value;
                const url = `/payment/credit?cvv=${CVV}`;
                const json = await pay.fetchFromApi(url)
                console.log(json);
                if (json.isSuccessPayment == true){
                    console.log("Validation was successfull please close the modal!");
                    const paymentCheckUrl=`/payment`;
                    await pay.fetchFromApi(paymentCheckUrl);
                }

            }

        }
        else{
            console.log("alert");
        }
    },

    async creditHandler() {


    },

    async payPalHandler() {




    },

    async fetchFromApi(url) {
        const response = await fetch(url);
        return response.json();
    },

    loaderAnimation(){

    },
}
pay.init();


