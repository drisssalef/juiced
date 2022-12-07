let fruitList = [];
let juice = [];
let orders = [];

let ordernummer = 0;
let tottaleprijs = 0 ;



/*------------------------prepared Juice---------------------------*/

function preparedJuice(id,price,description) {

    let  buildPreparedJuice = {
        "juice_id":id ,
        "price_juice":price,
        "fruits": [],
        "description_fruit":description,
        "visibleJuice": 1,


    }
    return buildPreparedJuice;
}

function addPreparedJuicetolist(id,price,description) {

    let j = preparedJuice(id, price,description);
    juice.push(j);

    updateHtmlJuice();
}

/*-----------------fruit------------------------------------*/

function addFruitToList(id,name,price,description) {
    let buildedFruit = buildFruit(id, name,price,description);
    fruitList.push(buildedFruit);

    updateHtmlListFruit();

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


function updateHtmlListFruit(){
    let list = document.getElementById("fruitCurrentJuice");
    document.getElementById("fruitCurrentJuice").innerHTML = '';

    fruitList.forEach((item) => {

        let li = document.createElement("li");
        li.innerText = item.fruit_name + " " + item.price_fruit;
        list.appendChild(li);
    });
}


function removeFruitFromList(fruit_id){
    if(fruitList.findIndex( el => el.fruit_id == fruit_id) >-1){
        fruitList.splice( fruitList.findIndex(el => el.fruit_id == fruit_id),1);
    }
    updateHtmlListFruit();

}

/*----------------------------- Juice----------------------------------------*/

async function createJuice() {
    //method om price
    let sum = 0;
    fruitList.forEach(fruit => {
        sum +=  +fruit.price_fruit;
    })

    juice.push({
        'price_juice': sum,
        'fruits': fruitList,
        'description_fruit': "Dit is jouw nieuwe juice",
        'visibleJuice': 0

        //do the rest
    }) ;
    updateHtmlJuice();
    /*let res = await fetch('/api/v1/juice');
    let data = await res.json();
    console.log(data);*/
}


function updateHtmlJuice(){

    let list = document.getElementById("createdJuice");
    document.getElementById("createdJuice").innerHTML = '';
    juice.forEach((item) => {

        let li = document.createElement("li");
        li.innerText = "Gemaakte Juice prijs =" + item.price_juice;
        list.appendChild(li);
    });

    fruitList = [];
    updateHtmlListFruit()

   }

   /*----------------------------- Orders----------------------------------------*/

   function createOrders(){
    console.log(orders.length);
    if(orders.length <1){
        ordernummer += 1;

        console.log("test")
        orders.push({
            "orders_nummer": ordernummer++,
            'orders_product_desc': "Uw order is uitgevoerd",
            'orders_is_paid':0,
            'juices':  juice.map(a => ({...a}))
        })
 
 
    }
    updateHTMLorder();
    
    console.log(orders);
   }



function updateHTMLorder(){

    let juichtml = document.getElementById("createdJuice");
    document.getElementById("createdJuice").innerHTML = '';



    juice.forEach(((item) => {
         tottaleprijs += +item.price_juice

    }));

    let list = document.getElementById("orderlist");
    document.getElementById("orderlist").innerHTML = '';
    console.log(orders);
    orders.forEach((item) => {

        let li = document.createElement("li");
        //<p class="lead">USD 9.99</p>
        li.innerHTML = `<span> order nummer ${item.orders_nummer} Total price: <p class="lead"> USD ${tottaleprijs}</p> </span>`
        //li.innerText = "Order nummer=" + item.orders_nummer + " TottalePrijs = " + tottaleprijs;
        list.appendChild(li);

        /*item.juices.forEach((item) => {
            let li = document.createElement("li");

            li.innerText = "juice: " + item.description_juice + " price: "+ item.price_juice;
            list.appendChild(li);
        })*/
    });
    juice = [];
    //tottaleprijs = 0;

}



