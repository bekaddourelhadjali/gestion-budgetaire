$(document).ready(function(){
  $(".del").click(function(){
    if (!confirm("Do you want to delete")){
      return false;
    }else{
        return true;
    }
  });
//  $("input[type=search]").keyup(function(){
//      document.location.href="CommandelistServlet?command=search&search="+$(this).val();
//     
//  });
   
});

