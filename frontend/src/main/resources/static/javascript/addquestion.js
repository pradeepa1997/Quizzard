
function onLoad(){
    setTimeout(()=>{
        document.getElementById('alert')&&(document.getElementById('alert').style.display="none");
    },3000);
}
// var message = "[[${message}]]";
function hell(a,b){
    // var number = [[${number}]];
    
    document.getElementById("modaltittle").innerHTML=a;
    document.getElementById("quizlink").href="/attemptquiz/"+b;

    
    console.log(a,b);

}

function hell2(a){
    // var number = [[${number}]];
    
    document.getElementById("hell3").value=a;
    // document.getElementById("quizlink").href="/attemptquiz/"+b;

    
    console.log("huuuuuuuuuu");

}