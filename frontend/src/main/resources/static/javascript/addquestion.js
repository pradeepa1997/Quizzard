
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