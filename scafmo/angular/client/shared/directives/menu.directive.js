'use strict';

angular.module('angularDemoApp')
  .directive('ngMenu', function() {
    return {
      restrict: 'AC',
      link: function(scope, el) {
        var _window = $(window), 
        _mb = 768, 
        wrap = $('.app-aside'), 
        next, 
        backdrop = '.dropdown-backdrop';
        // unfolded
        el.on('click', 'a', function(e) {
          if(next){
          	next.trigger('mouseleave.nav');
          }
          var _this = $(this);
          _this.parent().siblings('.active').toggleClass('active');
          if(_this.next().is('ul')){
          	_this.parent().toggleClass('active');
          	e.preventDefault();
          }
          // mobile
         if(_this.next().is('ul') || ( _window.width() < _mb )){
         	$('.app-aside').removeClass('show off-screen') ;
         }
        });

        // folded & fixed
        el.on('mouseenter', 'a', function(e){
          if(next){
          	next.trigger('mouseleave.nav');
          }
          $('> .nav', wrap).remove();
          if ( !$('.app-aside-fixed.app-aside-folded').length || ( _window.width() < _mb ) || $('.app-aside-dock').length){
          	 return;
          }
          
          var _this = $(e.target), top, winHeight = $(window).height(), offset = 50, min = 150;

          if(!_this.is('a')){
          	_this = _this.closest('a');
          }
          if( _this.next().is('ul') ){
             next = _this.next();
          }else{
            return;
          }
         
          _this.parent().addClass('active');
          top = _this.parent().position().top + offset;
          next.css('top', top);
          if( top + next.height() > winHeight ){
            next.css('bottom', 0);
          }
          if(top + min > winHeight){
            next.css('bottom', winHeight - top - offset).css('top', 'auto');
          }
          next.appendTo(wrap);

          next.on('mouseleave.nav', function(){
            $(backdrop).remove();
            next.appendTo(_this.parent());
            next.off('mouseleave.nav').css('top', 'auto').css('bottom', 'auto');
            _this.parent().removeClass('active');
          });

          if($('.smart').length){
          	$('<div class="dropdown-backdrop"/>').insertAfter('.app-aside').on('click', function(next){
	            if(next){
	            	next.trigger('mouseleave.nav');
	            }
	        });
          }

        });

        wrap.on('mouseleave', function(){
          if(next){
          	next.trigger('mouseleave.nav');
          }
          $('> .nav', wrap).remove();
        });
      }
    };
  });