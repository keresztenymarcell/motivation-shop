function addEventHandlersToItemCountButtons(){
    const minusButtons = document.querySelectorAll("#btnMinus");
    const plusButtons = document.querySelectorAll("#btnPlus");

    minusButtons.forEach(button => {
        button.addEventListener("click", () => {
        })
    })

    plusButtons.forEach(button => {
        button.addEventListener("click", (e) => {
            changeCounterValue(e, 1);
            addCountAndFetch();
            fetchOrder()
        })
    })
}

function changeCounterValue(e, value){
    const productId = e.target.dataset.productId;
    const countDiv = document.getElementById("count" + productId);
    let count = countDiv.innerHTML;
    count = parseInt(count, 10) + value
    countDiv.innerHTML = count;
}

function addCountAndFetch(productId){
    const url = `/api/add-to-cart?id=${productId}`
    fetchFromApi(url);
}

function fetchOrder(){
    const url = `/api/order`;
    fetchFromApi(url);
}

function fetchFromApi(url){
        const response = fetch(url);
        return response.json();
}

addEventHandlersToItemCountButtons()