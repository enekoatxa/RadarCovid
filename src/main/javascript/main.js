function navigate(page){
  for (var i = 1; i <=5; i++) {
    if(page==i){
      $("#page"+i).show();
    }
    else{
      $("#page"+i).hide();
    }
  }
  if(page==3){
   	initMap2();
  }
}