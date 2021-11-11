function addEventHandlersToItemCountButtons(){
    const minusButtons = document.querySelectorAll("#btnMinus");
    const plusButtons = document.querySelectorAll("#btnPlus");

    minusButtons.forEach(button => {
        button.addEventListener("click", () => {
        })
    })

    plusButtons.forEach(button => {
        button.addEventListener("click", async (e) => {
            changeCounterValue(e, 1);
            const productId = e.target.dataset.productId;
            await addCountAndFetch(productId);
            await updateCart();
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

async function updateCart(){
    const cart = await fetchOrder();
    const itemTotalDivs = document.getElementsByClassName("amount");

    for (let i = 0; i < cart.length; i++) {
            itemTotalDivs[i].innerHTML = '$' + cart[i].itemTotal;
    }
}

async function addCountAndFetch(productId){
    console.log(productId);
    const url = `/api/add-to-cart?id=${productId}`
    await fetchFromApi(url);
}

async function fetchOrder(){
    const url = `/api/order`;
    return await fetchFromApi(url);
}

async function fetchFromApi(url){
        const response = await fetch(url);
        return response.json();
}

addEventHandlersToItemCountButtons()