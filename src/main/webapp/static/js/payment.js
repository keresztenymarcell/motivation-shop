const pay = {
    init() {
        const myModal = new window.bootstrap.Modal(document.getElementById('staticBackdrop'), {
            keyboard: false
        });
        const creditBtn = document.getElementById('credit-card-button');
        const paypalBtn = document.getElementById('paypal-button');
        creditBtn.addEventListener("click", pay.creditHandler);
        paypalBtn.addEventListener("click", pay.payPalHandler);

    },
    creditHandler() {
        const cardHolder = document.getElementById('cardholder').value;
        console.log(cardHolder);

    },

    payPalHandler() {

    },
}
pay.init();


