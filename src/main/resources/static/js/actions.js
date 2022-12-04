let fruitList = []
let juice = {};
function demo(fruit) {
    fruitList.push(fruit);
    console.log(fruitList)
    createJuice().then(r => console.log("success"));
}

async function createJuice() {
    //method om price
    let sum = 0;
    fruitList.forEach(fruit => {
        sum +=  +fruit.price_fruit;
    })

    juice = {
        'price_juice': sum,
        'fruits': fruitList,
        'description_fruit': "Dit is jouw nieuwe juice",
        'visibleJuice': 0

        //do the rest
    }
    console.log(juice);
    updateHtmlJuice();
    /*let res = await fetch('/api/v1/juice');
    let data = await res.json();
    console.log(data);*/
}


function addFruitToList(id,name,price,description) {
    let buildedFruit = buildFruit(id, name,price,description);
    fruitList.push(buildedFruit);

    updateHtmlListFruit();

}
function updateHtmlListFruit(){
    let list = document.getElementById("fruitCurrentJuice");
    document.getElementById("fruitCurrentJuice").innerHTML = '';

    fruitList.forEach((item) => {

        let li = document.createElement("li");
        li.innerText = item.fruit_name + " " + item.price_fruit;
        list.appendChild(li);
    });
}
function buildFruit(id,name,price,description){
    let buildedFruit = {
        "fruit_id":id,
        'fruit_name':name,
        'price_fruit':price,
        'description_fruit':description
    }
    return buildedFruit;
}


function removeFruitFromList(fruit_id){
    if(fruitList.findIndex( el => el.fruit_id == fruit_id) >-1){
        fruitList.splice( fruitList.findIndex(el => el.fruit_id == fruit_id),1);
    }
    updateHtmlListFruit();

}

function updateHtmlJuice(){
    console.log(juice);
    let list = document.getElementById("createdJuice");
    document.getElementById("createdJuice").innerHTML = '';

    let span = document.createElement("span") ;
    span.innerText = "Prijs gemaakte juice = " + juice.price_juice
    list.appendChild(span);

    fruitList = [];
    updateHtmlListFruit()

}

