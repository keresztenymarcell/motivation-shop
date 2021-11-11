function addEventHandlersToItemCountButtons(){
    const minusButtons = document.querySelectorAll("#btnMinus");
    const plusButtons = document.querySelectorAll("#btnPlus");

    minusButtons.forEach(button => {
        button.addEventListener("click", async (e) => {
            changeCounterValue(e, -1);
            const productId = e.target.dataset.productId;
            await reduceCountAndFetch(productId);
            await updateCart();
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

async function removeCartItem(productId) {
    const count = document.getElementById("count" + productId).innerHTML;
    const cartItem = document.getElementById("cartItem" + productId);
    for (let i = 0; i < count; i++) {
        await reduceCountAndFetch(productId);
    }
    cartItem.innerHTML = "";
}

function addEventHandlersToRemoveItemButtons(){
    const removeItemButtons = document.querySelectorAll(".remove")

    removeItemButtons.forEach(button => {
        button.addEventListener("click",  async (e) => {
            const productId = e.target.parentNode.dataset.productId;
            await removeCartItem(productId)
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
    const order = await fetchOrder();
    const itemTotalDivs = document.getElementsByClassName("amount");
    const subTotalDiv = document.querySelectorAll(".total-amount")[0];
    const itemsDiv = document.querySelectorAll(".items")[0];
    const cartItemsDivs = document.querySelectorAll(".Cart-Items");
    const countDivs = document.querySelectorAll(".count");

    subTotalDiv.innerHTML = order.orderTotalValue;
    itemsDiv.innerHTML = order.totalItems + ' items';

    for (let i = 0; i < order.cart.length; i++) {
        console.log(countDivs[i].innerHTML);
        if(parseInt(countDivs[i].innerHTML)===0){
            cartItemsDivs[i].innerHTML = "";
        }
        itemTotalDivs[i].innerHTML = '$' + order.cart[i].itemTotal;
    }
}


async function reduceCountAndFetch(productId){
    const url = `/api/remove-from-cart?id=${productId}`
    await fetchFromApi(url);
}

async function addCountAndFetch(productId){
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

addEventHandlersToItemCountButtons();
addEventHandlersToRemoveItemButtons();
