
function onLoad(){
    setTimeout(()=>{
        document.getElementById('alert')&&(document.getElementById('alert').style.display="none");
    },3000);
}
var message = "[[${message}]]";
function hell(){
    // var number = [[${number}]];

    
    console.log(message);

}