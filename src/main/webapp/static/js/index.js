const main = {
    init() {
        const categories = document.getElementById("categories");
        categories.addEventListener("mouseup", async (event) => {
            const categoryId = event.target.value;
            const data = await main.fetchFromApi(`/api/filter?name=category&id=${categoryId}`);
            console.log(data);
        })
    },
    async fetchFromApi(url) {
        const response = await fetch(url);
        return response.json();
    }

}
main.init();