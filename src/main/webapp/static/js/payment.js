const pay = {
    init() {
        const myModal = new window.bootstrap.Modal(document.getElementById('staticBackdrop'), {
            keyboard: false
        });
        const creditBtn = document.getElementById('credit-card-button');
        const paypalBtn = document.getElementById('paypal-button');
        creditBtn.addEventListener("click", pay.creditHandler);
        paypalBtn.addEventListener("click", pay.payPalHandler);
        const loaderButton = document.getElementById("loader");
        loaderButton.addEventListener("click", pay.loaderAnimation);

    },
    async creditHandler() {
        const CVV = document.getElementById('cvv').value;
        const url = `/payment/credit?cvv=${CVV}`;
        const isSuccess = await pay.fetchFromApi(url)
        console.log(isSuccess);
        if (isSuccess == "true"){
            const paymentCheckUrl=`/payment`;
            await pay.fetchFromApi(paymentCheckUrl);
        }
        else{
            console.log("alert");
        }

    },

    async payPalHandler() {
        const payPalUser = document.getElementById('paypal-user').value;
        const url = `/payment/paypal?user=${payPalUser}`;
        const isSuccess = await pay.fetchFromApi(url)
        console.log(isSuccess);
        if (isSuccess == "true"){
            const paymentCheckUrl=`/payment`;
            await pay.fetchFromApi(paymentCheckUrl);
        }
        else{
            console.log("alert");
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


