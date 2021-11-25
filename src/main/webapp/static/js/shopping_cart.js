function addEventHandlersToItemCountButtons(){
    const minusButtons = document.querySelectorAll("#btnMinus");
    const plusButtons = document.querySelectorAll("#btnPlus");

    minusButtons.forEach(button => {
        button.addEventListener("click", async (e) => {
            await changeCounterValue(e, -1);
            const productId = e.target.dataset.productId;
            await reduceCountAndFetch(productId);
            await updateCart();
        })
    })

    plusButtons.forEach(button => {
        button.addEventListener("click", async (e) => {
            await changeCounterValue(e, 1);
            const productId = e.target.dataset.productId;
            await addCountAndFetch(productId);
            await updateCart();
        })
    })
}

function addEventHandlerToRemoveAllButton(){
    const button = document.getElementById("removeAll");
    const itemsContainer = document.querySelectorAll(".items-container");

    button.addEventListener("click", async () => {
        await doDeleteFetch('/api/cart')
        itemsContainer[0].innerHTML = "";
        const itemsDiv = document.querySelectorAll(".items")[0];
        const subTotalDiv = document.querySelectorAll(".total-amount")[0];
        subTotalDiv.innerHTML = "$0";
        itemsDiv.innerHTML =  "0 items";
    })

}

function addEventHandlersToRemoveItemButtons(){
    const removeItemButtons = document.querySelectorAll(".remove")

    removeItemButtons.forEach(button => {
        button.addEventListener("click",  async (e) => {
            const productId = e.target.parentNode.dataset.productId;
            await removeCartItem(productId);
            await updateCart();
        })
    })
}

async function changeCounterValue(e, value){
    const productId = e.target.dataset.productId;
    const countDiv = document.getElementById("count" + productId);
    let count = countDiv.innerHTML;
    count = parseInt(count, 10) + value
    countDiv.innerHTML = count;
    if(parseInt(count) === 0){
        await removeCartItem(productId);
    }
}

async function removeCartItem(productId) {
    const count = document.getElementById("count" + productId).innerHTML;
    const cartItem = document.getElementById("cartItem" + productId);
    for (let i = 0; i < count; i++) {
        await reduceCountAndFetch(productId);
    }
    cartItem.innerHTML = "";
}

async function updateCart(){
    const cart = await fetchOrder();
    const itemTotalDivs = document.getElementsByClassName("amount");
    const subTotalDiv = document.querySelectorAll(".total-amount")[0];
    const itemsDiv = document.querySelectorAll(".items")[0];
    const cartItemsDivs = document.querySelectorAll(".Cart-Items");
    const countDivs = document.querySelectorAll(".count");

    subTotalDiv.innerHTML = '$' + cart.totalPrice;
    itemsDiv.innerHTML = cart.totalItems + ' items';

    for (let i = 0; i < cart.lineItems.length; i++) {
        if(parseInt(countDivs[i].innerHTML)===0){
            cartItemsDivs[i].innerHTML = "";
        }
        itemTotalDivs[i].innerHTML = '$' + cart.lineItems[i].quantity * cart.lineItems[i].product.defaultPrice;
    }
}


async function reduceCountAndFetch(productId){
    const url = `/api/cart?id=${productId}`
    await doPostFetch(url);

}

async function addCountAndFetch(productId){
    const url = `/api/cart?id=${productId}`
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

async function doDeleteFetch(url, ){
    const response = fetch(url, {
        method : 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify('')
    });
    const content = (await response).json()
}

async function doPostFetch(url){
    const response = fetch(url, {
        method : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify('')
    });
    const content = (await response).json()
}

addEventHandlersToItemCountButtons();
addEventHandlersToRemoveItemButtons();
addEventHandlerToRemoveAllButton();
