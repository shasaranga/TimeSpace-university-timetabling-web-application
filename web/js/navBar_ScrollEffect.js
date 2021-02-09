// $(window).scroll(function(){
//
//    var windowsize = $(window).width();
//    if(windowsize > 300){
//       $('nav').toggleClass('scrolled', $(this).scrollTop() > 960);
//    }else{
//       $('nav').toggleClass('.bg-dark', $(this).scrollTop() > 200);
//    }
//
// });
//
// $(window).scroll(function(){
//    var windowsize = $(window).width();
//    if($(this).scrollTop() > 960){
//       $('.onScrollNavColorChange').addClass('onScrollNav');
//
//    }else{
//
//       $('.onScrollNavColorChange').removeClass('onScrollNav');
//    }
// });

const nav = document.querySelector("nav");

const sectionOne = document.querySelector(".navChangeLimit");
const sectionOneOptions = {
   //root :null //it means its the viewport
   //threshold: between 0-1 scale
   rootMargin: "-550px 0px 0px 0px"
};

const sectionOneObserver = new IntersectionObserver(function(entries,sectionOneObserver){

   entries.forEach(entry =>{
      console.log(entry.target); //gets the html code
      if(!entry.isIntersecting){
         nav.classList.add("scrolled");
         $('.onScrollNavLinkColorChange').addClass('onScrollNav');
      }else{
         nav.classList.remove("scrolled");
         $('.onScrollNavLinkColorChange').removeClass('onScrollNav');
      }
   })
}, sectionOneOptions);
sectionOneObserver.observe(sectionOne);