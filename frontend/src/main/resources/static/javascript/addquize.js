
function onLoad(){
    setTimeout(()=>{
        document.getElementById('alert').style.display="none";
    },3000);
    
    if(document.getElementById('quizeCategory').value=="other"){
        document.getElementById('othertype').disabled = false;
        document.getElementById('othertype').required = true;

    }else{
        document.getElementById('othertype').value="";
        document.getElementById('othertype').required = false;
        document.getElementById('othertype').disabled = true;
    }
}

function changedisblity(event){
    
    if(event.target.value=="other"){
        document.getElementById('othertype').disabled = false;
        document.getElementById('othertype').required = true;

    }else{
        document.getElementById('othertype').value="";
        document.getElementById('othertype').required = false;
        document.getElementById('othertype').disabled = true;
    }
}