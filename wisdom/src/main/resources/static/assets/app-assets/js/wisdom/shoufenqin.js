@font-face{
    font-family: "Siyuan";
    src:url('../font/NOTOSANSHANS-REGULAR_2.OTF');
    font-weight: normal;
    font-style: normal;
}
.statement{
    box-sizing: border-box;
    padding:2rem 0 3.5rem;
}
.statement h2{
    text-align: center;
    color: #404040;
    font-size: 1rem;
    margin-bottom: 1.5rem;
}
.statement p{
    text-indent: 1.4rem;
    line-height: 1.6;
    color: #666;
    font-size: .7rem;
    text-align: justify;
}
.container1800{
    max-width: 1800px;
    margin:0 auto;
}
.loading{
    width:100vw;
    height: 100vh;
    position: fixed;
    left:0;
    top:0;
    z-index:20000;
}
.load_con{
    width:300px;
    position: relative;
    top:50.1vh;
    transform: translateY(-50%);
    margin:0 auto;
    height:140px;
}
.loadImg_box{
    width:100%;
    text-align: center;
    height:60px;
    overflow: hidden;
}
.load_t{
    position: absolute;
    width:100%;
    top:0;
    height:50%;
    background: #fff;
    transition: .8s;
}
.load_t_l{
    position: absolute;
    right:50%;
    bottom:0;
    height:4px;
    background:#d6000f;
    width:0;
    transition: all .8s ease-in-out;
}
.load_t_r{
    position: absolute;
    left:50%;
    bottom:0;
    height: 4px;
    width:0;
    background:#d6000f;
    transition: all .8s ease-in-out;
}
.load_t_active_lr{
    width:50%;
}
.load_b{
    width:100%;
    height:50vh;
    position: absolute;
    bottom:0;
    background: #fff;
    transition: .8s;
}
.active_load_t{
    top:-50.1%;
}
.active_load_b{
    bottom:-50%;
}
.loadImg_box img{
    transition: .8s;
    display: inline-block;
}
.loadImg_box_active{
    transform: translateY(140%);
}

/*index*/
.container{
    max-width:1200px;
    margin:0 auto;
}
.container1{
    max-width:1800px;
    margin:0 auto;
}
.footer{
    width:100%;
}
.footer_top{
    height:50px;
    line-height: 50px;
    border-bottom:1px solid #dedede;
}
.foot_t_l{
    display: inline-block;
    vertical-align: middle;
    box-sizing: border-box;
    border-bottom: 2px solid #d6000f;
}
.foot_t_l span{
    color: #666;
    font-size:.7rem;
    display: inline-block;
    margin-right: .5rem;
}
.footer_share{
    display: inline-block;
    vertical-align: middle;
}
.foot_t_l>.footer_share>a{
    display: inline-block;
    vertical-align: middle;
    padding-left: 0!important;
}
.footer_share .bds_tsina,.footer_share .bds_weixin{
    width:20px;
    height:16px;
    display: inline-block;
    vertical-align: middle;
    margin-right: .5rem;
}
.footer_share .bds_tsina{
    background: url('../img/wb.png')no-repeat center!important;

}
.footer_share .bds_weixin{
    background: url('../img/wx.png')no-repeat center!important;

}
.footer_share .bds_sqq{
    display: inline-block;
    width: 15px;
    height: 16px;
    background: url('../img/qq.png')no-repeat center!important;
}
.foot_t_r{
    display: inline-block;
    vertical-align: middle;
}
.foot_t_r ul{
    font-size:0;
    height:50px;
}
.foot_t_r ul li{
    display: inline-block;
    vertical-align: middle;
    box-sizing: border-box;
    padding:0 1rem 0 1rem;
    line-height: 50px;
    position: relative;
}
.foot_t_r ul li::before{
    content:'';
    display:inline-block;
    width:10px;
    height:2px;
    background: #ccc;
    position: absolute;
    left:-5px;
    top:50%;
    transform:translateY(-50%);

}
.foot_t_r ul li:nth-of-type(1)::before{
    display: none;
}
.foot_t_r ul li a{
    font-size:.7rem;
    color: #666;
    display: block;
    width:100%;
    height:100%;
}
.footer_bottom{
    height:60px;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    padding:.5rem 0;
}
.footer_b_l{
    display: inline-block;
    vertical-align: top;
}
.footer_bottom p{
    line-height: 2;
    font-size: .6rem;
    color: #666;
    opacity: .7;
}
.footer_bottom p a{
    color: #666;
}
.foot_b_r{
    display: inline-block;
    vertical-align: top;
    position: relative;
}
.source_link{
    z-index: 25000;
    position: absolute;
    bottom: 27px;
    right: 0;
    width: 99%;
    border-left: 1px solid #dedede;
    border-right: 1px solid #dedede;
    border-top: 1px solid #dedede;
    background: #f5f5f5;
    border-top-right-radius: 8px;
    border-top-left-radius: 8px;
    display: none;
}
.source_link a:nth-of-type(1){
    border-top: 0px solid transparent;
}
.source_link a{
    display: block;
    line-height: 2rem;
    border-top:1px solid #dedede;
    box-sizing: border-box;
    padding:0 1rem;
    color: #999;
    font-size: .7rem;
}
.foot_b_r span{
    display: block;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
    border:1px solid #dedede;
    height:30px;
    width:150px;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    padding:.2rem 1rem;
    position: relative;
    color: #999;
    font-size:.7rem;
    line-height: 22px;
    cursor: pointer;
}
.foot_b_r span i{
    position: absolute;
    right:1rem;
    top:50%;
    display: inline-block;
    display: inline-block;
    width: 10px;
    height: 10px;
    transition: .3s;
    transform: translateY(-50%);
    background: url('../img/down.png')no-repeat center;
}
.foot_b_r span i.addRotate{
    transform: translateY(-50%) rotate(180deg);
}
.index_carousel{
    width:100%;
}
.index_carousel .swiper-slide{
    width:100%;
}
.index_carousel .swiper-slide>a{
    display: block;
    width:100%;
    height:100%;
}
.index_carousel .swiper-slide>a img{
    width:100%;
    vertical-align: top;
}
.header{
    width:100%;
    height:80px;
}
.logo_box{
    display: inline-block;
    height:80px;
    vertical-align: top;
    line-height: 80px;
    width:16.5%;
}
.logo_box img{
    width:100%;
    vertical-align: middle;
}
.header_nav{
    display: inline-block;
    vertical-align: middle;
    width:55%;
    margin-left: 9%;
}
.header_nav ul{
    width:100%;
    font-size:0;

}
.header_nav li{
    display: inline-block;
    vertical-align: middle;
    width:16.5%;
    transition: .3s;
    height:80px;
    line-height:80px;
    text-align: center;
}

.header_nav li>a{
    color: #333;
    font-size:.8rem;
    display: inline-block;
    height:100%;
    transition: .3s;
    position: relative;
}
.header_nav li>a::after{
    content: "";
    width: 0%;
    position: absolute;
    bottom: 0;
    left: 0!important;
    height: 2px;
    background: #d6000f;
    transition: .5s ease-in-out;
}
.header_nav li:hover a::after{
    width: 100%;
}
.header_nav li:hover a{
    /*font-weight: 600;*/
    color: #d6000f;
}
.header_nav ul .first_li a{
    color: #d6000f;
}

.search_box{
    display: inline-block;
    vertical-align: middle;
    width:5%;
}
.translate{
    display: inline-block;
    vertical-align: middle;
    width:13%;
}
#neiwang{
    color: #666;
    font-size: .8rem;
    display: inline-block;
    vertical-align: middle;
    margin-left: 25%;
}
.sceond_nav{
    position: absolute;
    top:80px;
    left:0;
    text-align: center;
    z-index: 2000;
    background: #d6000f;
    height:40px;
    line-height: 40px;
    display: none;
    width:100%;
}
.sceond_nav p{
    text-align: center;
}
.sceond_nav p a{
    display: inline-block;
    vertical-align: middle;
    padding:0 2rem;
    color: #fff;
    font-size:.8rem;
}
.form{
    width:100%;
    position: relative;
    height:30px;
    border-left:1px solid #dedede;
}
.form input{
    position: absolute;
    top:50%;
    right:.5rem;
    width:0px;
    display: inline-block;
    height:40px;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    border:1px solid #dedede;
    padding:2px 10px;
    color: #666;
    opacity:0;
    transform: translateY(-50%);
    transition: .5s;
    font-size:.8rem;
    line-height:36px;
}
.form .inp_show{
    width:400px;
    opacity:1;
}
.header_out_div{
    position: relative;
}
.search_icon{
    display: inline-block;
    position: absolute;
    left:30%;
    width:19px;
    height:19px;
    cursor: pointer;
    outline:none;
    border:none;
    background: #000;
    top:50%;
    -webkit-transform: translateY(-50%);
    -moz-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    -o-transform: translateY(-50%);
    transform: translateY(-50%);
    background: url('../img/search.png')no-repeat center;
}
.search_icon.after::after{
    content: "";
    display: block;
    width: 1px;
    height: 160%;
    position: absolute;
    left: -.6rem;
    top: 50%;
    transform: translateY(-50%);
    background: #dedede;
    transition: .5s;
}
.translate .en{
    color: #666;
    font-size:.8rem;
    vertical-align: middle;
}
.translate .cn{
    color: #d6000f;
    font-size:.8rem;
    vertical-align: middle;
}
.index_carousel .swiper-button-next{
    width: 26px;
    height: 26px;
    background: url('../img/next.png')no-repeat center;
    outline: none;
    cursor: pointer;
    right: 15%;
}
.index_carousel .swiper-button-prev{
    width: 26px;
    height: 26px;
    background: url('../img/prev.png')no-repeat center;
    outline: none;
    cursor: pointer;
    left: 15%;
}
.shoufenqin{
    width: 100%;
    height: 160px;
}
.shoufenqin_box{
    width: 100%;
    height: 160px;
    font-size: 0;
    overflow: hidden;
}
.shoufenqin_box>div{
    display: inline-block;
    vertical-align: top;
    position: relative;
    height: 160px;
    width: 120px;
    /*transition: .5s;*/
}
.shoufenqin_box>div .big_sfq{
    opacity: 0;
    transition: .3s;
}
.shoufenqin_box>div span{
    display: inline-block;
    width: 16px;
    height: 100%;
    font-size: .7rem;
    color: #fff;
    padding:1rem 1rem;
    box-sizing: border-box;
}
.shoufenqin_box>div>a>img{
    position: absolute;
    bottom: 1rem;
    right: 1rem;
}
.one2 .big_sfq>div>p,.one2 .big_sfq>div>a{
    color: #999!important;
}
.one2{
    background: #fcead4;
}
.one3{
    background: #ffab74;
}
.one4{
    background: #ec7365;
}
.one5{
    background: #f1c65b;
}
.one6{
    background: #4d79c2;
}
.one7{
    background: #2f2f5c;
    position: absolute!important;
    right: 0;
    top: 0;
    z-index: 2510;
}
.one3 .big_sfq>div>p,.one4 .big_sfq>div>p,.one5 .big_sfq>div>p,.one6 .big_sfq>div>p,.one7 .big_sfq>div>p{
    color: #fff!important;
}
.one3 .big_sfq>div>a,.one4 .big_sfq>div>a,.one5 .big_sfq>div>a,.one6 .big_sfq>div>a,.one7 .big_sfq>div>a{
    color: #fff!important;
}
.one3 .big_sfq>div>a i,.one4 .big_sfq>div>a i,.one5 .big_sfq>div>a i,.one6 .big_sfq>div>a i,.one7 .big_sfq>div>a i{
    color: #fff!important;
}
.shoufenqin_box{
    position: relative;
    background: #2f2f5c;
}
.shoufenqin_box .one1{
    background: #f9f9f9;
}
.big_sfq>img{
    width: 100%;
    vertical-align: top;
}
.big_sfq>div{
    position: absolute;
    left: 0;
    top: 0;
    padding:1rem;
    box-sizing: border-box;
}
.big_sfq>div>p{
    color: #666;
    font-size: .8rem;
    margin:1rem 0;
}
.big_sfq>div>a{
    color: #666;
    font-size: .6rem;

}
.big_sfq>div>a>i{
    display: inline-block;
    vertical-align: middle;
    margin:0 .5rem 0 0;
    color: #b6b6b6;
}
.one1>a>span{
    color: #666;
}
.one2>a>span{
    color: #999;
}
.shoufenqin_box .len .big_sfq{
    opacity: 1;
}
.shoufenqin_box .len .small{
    opacity: 0!important;
}
.shoufenqin_box .small{
    position: absolute!important;
    left: 0!important;
    top: 0!important;
    opacity: 1!important;
    z-index: 200!important;
    transition: .3s;
    width: 100%;
    height: 100%;
}
.index_carousel_con .swiper-slide>a{
    position: relative;
}
.index_carousel_con .swiper-slide>a>video{
    width: 100%;
    vertical-align: top;
    transform: translateY(-13rem);
}
.index_mm .index_carousel_con .swiper-slide>a>video{
    transform: none;
    width: 100%;
}
.index_carousel_con{
    position: absolute!important;
    width: 100%!important;
    height: 100%!important;
    top: 0;
    left: 0;
}
.index_carousel_con .swiper-pagination-bullet{
    transition: 0.5s;
    -webkit-transition: 0.5s;
}
.index_carousel_con .swiper-pagination-bullet-active{
    background: #d6000f!important;
}

.index_carousel{
    position: relative;
    padding-bottom: 25%;
}
.index_cover{
    position: absolute;
    top: 0;
    bottom: 0;
    width: 100%;
    height: 100%;
    /* background: rgba(255,255,255,0.15); */
}
.index_cover h1{
    color: #fff;
    font-size: 1.7rem;
    text-align: center;
    margin-bottom: 1rem;
    font-family: "Siyuan";
}
.index_cover p{
    font-size: 1.1rem;
    color: #fff;
    line-height: 1.5;
    font-family: "Siyuan";
}
.index_cover>div{
    position: absolute;
    top: 58%;
    left: 17%;
    transform: translateY(-50%);
}
.index_cover1>div{
    position: absolute;
    top: 50%;
    right:-18%;
    transform: translateY(-50%);
}
.index_cover1>div img{
    width: auto!important;
}
.jiangxin_box{
    position: absolute;
    left: 35%;
    top: 45%;
    transition: 1s ease-in-out;
    transform: translateY(-50%);
    opacity: 0;
}
.active_jiangxin_box{
    top: 55%;
    opacity: 1;
}
.jiangxin_box img{
    width: auto!important;
}
.fuwu_png{
    position: absolute;
    right: 66%;
    bottom: 8%;
    transition: 2s ease-in-out;
    opacity: 0;
}
.active_fuwu_png{
    right: 57%;
    opacity: 1;
}
.index_carousel_con .swiper-slide>a{
    position: relative;
}
.banner{
    width: 100%;
}
.banner>.container1800{
    position: relative;
}
.banner>.container1800>img{
    width: 100%;
    vertical-align: top;
}
.banner>.container1800>.container{
    position: absolute;
    left: 50%;
    top: 0;
    height:100%;
    transform: translateX(-50%);
    z-index: 20;
    width: 66.66%;
    text-align: left;
}
.banner>.container1800>.container img{
    position: absolute;
    top: 50%;
    left: 0;
    transform: translateY(-50%);
}
.crumbs{
    height: 50px;
    line-height: 50px;
}
.crumbs .second_next{
    display: inline-block;
    vertical-align: middle;
    width: 6px;
    height: 12px;
    margin-right: .7rem;
    background: url('../img/second_next.png')no-repeat center;
}
.crumbs .home{
    margin-right: .7rem;
    display: inline-block;
    vertical-align: middle;
    width: 16px;
    height: 14px;
    background: url('../img/home.png')no-repeat center;
}
.crumbs a{
    color: #666;
    font-size: .7rem;
    margin-right: .7rem;
}
.second_nav{
    width: 100%;
    height: 50px;
}
.second_nav>.container1800{
    width: 100%;
    height: 50px;
    border-bottom: 1px solid #dedede;
}
.second_ul{
    font-size: 0;
}
.second_ul li{
    display: inline-block;
    vertical-align: middle;
    height: 50px;
    line-height: 50px;
    margin-right: 1.8rem;
    transition: .3s;
    position: relative;
}
.second_ul li a{
    display: block;
    width: 100%;
    height: 100%;
    color: #666;
    font-size: .7rem;
    position: relative;
    transition: .3s;
}
.second_ul li.active_li::after{
    content: '';
    display: block;
    width: 100%;
    height: 2px;
    background: #d6000f;
    bottom: 0;
    left: 50%;
}
.second_ul li.active_li a{
    color: #333;
    font-weight: 600;
    position: relative;
}
.second_ul li::after{
    content: '';
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    bottom: 0;
    height: 2px;
    width: 0;
    background: #d6000f;
    transition: .3s ease-out;
}
.second_ul li:hover::after{
    width: 100%;
}
.second_ul li:hover a{
    color: #333;
}
.org{
    padding:2rem 0 0 0;
    box-sizing: border-box;
}
.org h1{
    color:#404040;
    font-size: 1rem;
    font-weight: 600;
    margin-bottom: 2rem;
}
.org>.container{
    box-sizing: border-box;
    padding-bottom: 2.8rem;
}
.org>.container>img{
    width: 100%;
    vertical-align: top;
}
.footer>.container1800{
    background: #f9f9f9;
}
.business_con>.container{
    font-size: 0;
}
.business_con>.container .business_l{
    display: inline-block;
    vertical-align: top;
    width: 72.5%;
    box-sizing: border-box;
    border-right: 1px solid #dedede;
    padding-top:2rem;
    padding-right: 1.5rem;
}
.business_con>.container .business_r{
    display: inline-block;
    vertical-align: top;
    width: 27.5%;
    box-sizing: border-box;
    padding-left: 1.5rem;
    padding-top: 2rem;
    height: 100%;
    font-size: 0;
}
.business_after_r{
    padding-right: 1rem;
    padding-top: 4.8rem!important;
}
.bus_l_tip{
    width: 100%;
}
.tip_title{
    width: 100%;
    height: 1.5rem;
    border-bottom: 1px solid #dedede;
}
.bus_l_tip h1{
    color: #404040;
    font-size: 1rem;
    font-weight: 600;
    display: inline-block;
    vertical-align: middle;
}
.bus_l_tip img{
    width: 100%;
    vertical-align: top;
    margin-top: 1.4rem;
    margin-bottom: 1.5rem;
}
.bus_ver_line{
    display: inline-block;
    width: 4px;
    background: #d6000f;
    vertical-align: middle;
    margin-right: .5rem;
    height: 1.2rem;
}
.bus_title{
    width: 100%;
    height: 1.5rem;
    border-bottom: 1px solid #dedede;
    margin-bottom: 1rem;
}
.bus_title span{
    color: #404040;
    font-size: .8rem;
    font-weight: 600;
    position: relative;
    display: inline-block;
    height: 100%;
}
.bus_title span::after{
    content: "";
    display:block;
    position: absolute;
    left: 0;
    bottom: 0;
    height: 2px;
    width: 100%;
    background: #d6000f;
}
.bus_content{
    margin-bottom: 1.8rem;
}
.bus_content p{
    font-size: .7rem;
}
.bus_r_item{
    width: 47%;
    display: inline-block;
    vertical-align: top;
    background: #f9f9f9;
    padding:1.5rem 0 2.2rem 0;
    height: 100px;
    box-sizing: border-box;
    margin-right: 6%;
    position: relative;
    margin-bottom: 1.5rem;
    transition: .3s;
}
.bus_r_item:hover{
    box-shadow: 6px -6px 0px 0px rgba(204,204,204,.3);
}
.bus_r_item:nth-of-type(2n){
    margin-right: 0;
}
.bus_r_item>a>div{
    position: absolute;
    left: 0;
    top: 50%;
    width: 100%;
    text-align: center;
    transform: translateY(-50%);
}
.bus_r_item>a>div img{
    margin-bottom: .5rem;
    display: inline-block;
}
.bus_r_item>a>div p{
    font-size: .8rem;
    color: #666;
    line-height: 1.5;
}
.sousuojieguo{
    color: #333!important;
}
.search_list{
    width: 100%;
    font-size: 0;
    padding-bottom: 1.5rem;
    border-bottom: 1px solid #dedede;
    margin-bottom: 1rem;
}
.search_date{
    display: inline-block;
    vertical-align: top;
    height: 70px;
    box-sizing: border-box;
    padding:.6rem .35rem;
    background: #f5f5f5;
}
.search_date p{
    color: #999;
    font-size: .8rem;
    margin-bottom: .5rem;
}
.search_date span{
    color: #d6000f;
    font-size: 1.4rem;
    color: #df3c3d;
}
.search_words{
    display: inline-block;
    vertical-align: top;
    width: 90%;
    box-sizing: border-box;
    padding-left: 4%;

}
.search_words h1{
    font-size: .8rem;
    color: #404040;
    margin-bottom: 1rem;
    margin-top: .5rem;
    font-weight: 600;
    line-height: 20px;
    max-height: 20px;
    overflow: hidden;
}
.eye{
    display: inline-block;
    vertical-align: middle;
    width: 14px;
    height: 10px;
    background: url('../img/eye.png')no-repeat center;
    margin-right: .5rem;
}
.search_words p{
    font-size: .6rem;
    color: #999;
}
.search_more{
    font-size: .6rem;
    color: #999;
    text-transform: uppercase;
    display: inline-block;
    position: relative;
    padding-right: .5rem;
    transform: translateX(1rem);
    transition: .5s;
}
.search_list>a{
    display: block;
    width: 100%;
    height: 100%;
}
.search_more::after{
    display: block;
    content: "禄";
    position: absolute;
    right:0rem;
    font-size: 1rem;
    top:36%;
    opacity: 0;
    transition: .5s;
    transform: translateY(-50%);
}
.search_list:hover .search_more{
    color: #df3c3d;
    transform: translateX(.5rem);
    padding-right: 1rem;
}
.search_list:hover .search_more::after{
    opacity: 1;
    color: #df3c3d;
}
.page{
    text-align: center;
    margin-top:2rem;
    margin-bottom: 3rem;
}
.page a{
    display: inline-block;
    padding:.3rem .5rem;
    border:1px solid #dedede;
    box-sizing: border-box;
    vertical-align: middle;
    margin-right: .5rem;
    font-size: .6rem;
    color: #999;
    transition: .3s;
}
.page a:hover{
    color: #fff;
    background: #df3c3d;
    border:1px solid transparent;
}
.page a.active_a {
    background: #df3c3d;
    color: #fff;
    border:1px solid transparent;
}
.page a:last-of-type{
    margin-right: 0;
}
.search_r{
    padding:2rem 0 0 1.8rem!important;
}
.search_r>a{
    display: block;
    width: 100%;
}
.search_r>a>img{
    width: 100%;
    margin-bottom: 1rem;
}
.company_list{
    height: auto!important;
}
.company_l{
    display: inline-block;
    width: 44%;
    box-sizing: border-box;
    vertical-align: top;
    overflow:hidden;
}
.company_l img{
    width: 100%;
    transition: .8s;
}
.company_list:hover .company_l>img{
    transform: scale(1.1);
}
.company_r{
    display: inline-block;
    vertical-align: top;
    width: 56%;
    box-sizing: border-box;
    padding:0 0 0 2.8rem;
}
.company_list .company_r{
    background: url('../img/company_bac.png')no-repeat right top;
}
.company_list .search_words{
    padding-left: 0;
    width: 100%;
}
.company_list .search_words>h1{
    font-size: 1rem;
    color: #404040;
    line-height: 1.5;
    margin-bottom: 0rem;
    max-height: 60px;
    overflow: hidden;
}
.company_list .search_words>div p{
    line-height: 1.8;
    color: #777777;
    font-size: .7rem;
    text-align:justify;
    max-height: 50px;
    overflow: hidden;
}
.company_list .search_words .search_more{
    transform: translateX(.5rem);
}
.company_list .search_words>p{
    color: #999;
}
.company_list .search_words>div{
    margin-bottom: .9rem;
}
.company_otherList .search_words>h1{
    margin-top: 0;
    margin-bottom: .5rem;
    max-height: 20px;
    line-height: 20px;
    overflow: hidden;
    box-sizing: border-box;
}
.company_otherList .search_words>div{
    margin-bottom: .5rem;
}
.company_otherList .search_words{
    width: 91%;
}
.company_otherList .search_words .search_more{
    transform: translateX(.5rem);
}
.company_otherList .search_words>div p{
    line-height: 1.8;
    text-align: justify;
    color: #777777;
    font-size: .7rem;
    max-height: 50px;
    overflow: hidden;
}
.company_otherList .search_words>p{
    color: #999;
}
.newsDetail h2{
    font-size: 1rem;
    color: #404040;
    font-weight: 600;
    line-height: 20px;
    overflow: hidden;
    line-height: 2;
}
.newsDetail .search_words{
    border-bottom: 1px solid #dedede;
    box-sizing: border-box;
    padding-bottom: 1rem;
    margin-left: 3.4%;
    padding-left: 0;
}
.newsDetail .search_words{
    width: 86.6%;
    margin-bottom: .8rem;
}
.share{
    display: inline-block;
    vertical-align: middle;
    margin-left: 2.3rem;
}
.newsDetail .search_words>div p{
    display: inline-block;
    vertical-align: middle;
}
.share>a{
    padding-left: .2rem!important;
}
.share .bds_tsina{
    display: inline-block;
    width: 14px;
    height: 14px;
    background: url('../img/news_wb.png')no-repeat center!important;
}
.share .bds_weixin{
    display: inline-block;
    width: 14px;
    height: 14px;
    background: url('../img/news_wx.png')no-repeat center!important;
}
.share .bds_twi{
    display: inline-block;
    width: 14px;
    height: 14px;
    background: url('../img/news_tt.png')no-repeat center!important;
}
.share .bds_fbook{
    display: inline-block;
    width: 14px;
    height: 14px;
    background: url('../img/news_face.png')no-repeat center!important;
}
.newsDetail_con{
    width: 88.5%;
    float: right;
    padding-right: .5rem;
    line-height: 1.5;
    text-align: justify;
    box-sizing: border-box;
    text-align: center;
    padding-bottom: 1rem;
    border-bottom: 1px solid #dedede;
}
.newsDetail_con p{
    font-size: .7rem;
    color: #777777;
    text-align: justify;
    margin:.5rem 0 ;
}
.newsDetail_con img{
    max-width: 100%;
}
.newsDetail_page{
    box-sizing: border-box;
    width: 88%;
    float: right;
    margin:1.5rem 0 3rem 0;
}
.newsDetail_page>a{

    line-height: 1.8;
    display: inline-block;
    vertical-align: top;
    background: #f9f9f9;

    width: 50%;
    color: #666;
    font-size: 0;
}
.newsDetail_page>a span,.newsDetail_page>a p{
    display: inline-block;
    vertical-align: middle;
    font-size: .7rem;
    line-height: 1.8;
}
.newsDetail_page>a span{
    width: 27.5%;
    background: #eee;
    height: 90px;
    line-height: 90px;
    text-align: center;
    transition: .3s;
    font-size: .8rem;
}
.newsDetail_page>a p{
    width: 62.5%;
    max-height: 50px;
    overflow: hidden;
    padding:0rem .8rem 0rem .8rem;
}
.newsDetail_page>a:hover span{
    background: #d6000f;
    color: #fff;
}
.run_con{
    box-sizing: border-box;
    padding:2rem 0 0 0;
}
.structure>h2,.team>.container h2{
    font-size: 1rem;
    color: #404040;
    font-weight: 600;
    margin-bottom: 1rem;
}
.run_con>.container>.structure img{
    width: 100%;
    vertical-align: top;
    margin-bottom: 1.5rem;
}
.team{
    padding:1.5rem 0 1rem 0;
}
.team_l{
    width: 35%;
    box-sizing: border-box;
    vertical-align: top;
    display: inline-block;
}
.team_l_t img{
    width: 54.7%;
    display: inline-block;
    transition: .5s;
    transform: translateY(1rem);
    box-shadow: 20px -20px 0 #dddddd;
}
.Bigleader_intro{
    display: inline-block;
    width: 46%;
    box-sizing: border-box;
    padding-left: 10%;
    margin-top: -16%;
}
.Bigleader_intro h3{
    font-size: 1rem;
    color: #404040;
    font-weight: 600;
}
.Bigleader_intro h6{
    font-size: .7rem;
    color: #404040;
    line-height: 1.8;
    border-bottom: 1px solid #dedede;
    margin-bottom: .3rem;
    position: relative;
}
.Bigleader_intro h6::after{
    content: "";
    display: block;
    width: 40%;
    height: 2px;
    background: #d6000f;
    position: absolute;
    left: 0px;
    bottom: -1px;
}
.Bigleader_intro p span{
    display: block;
    color: #666;
    font-size: .6rem;
    line-height: 1.5;
}
.team_l_b{
    padding-top: 1rem;
    box-sizing: border-box;
}
.team_l_b h6{
    font-size: .7rem;
    color: #333;
    font-weight: 600;
    margin-bottom: .3rem;
}
.team_l_b p{
    color: #666;
    line-height: 1.8;
    text-align: justify;
    font-size: .7rem;
}
.team_r{
    padding-left:8%;
    box-sizing: border-box;
    width: 65%;
    display: inline-block;
    vertical-align:top;
}
.team>.container{
    font-size: 0;
}
.team_r>div{
    display: inline-block;
    vertical-align: top;
    width: 22%;
    margin-right: 4%;
    margin-bottom: .6rem;
    cursor: pointer;
}
.team_r>div:nth-child(4n){
    margin-right: 0;
}
.team_r>div img{
    width: 100%;
    transition: .5s;
}
.team_r>div:hover img{
    box-shadow: 10px -10px 0 #dddddd;
}
.team_r>div h5{
    color: #404040;
    font-size: .8rem;
    font-weight: 600;
    border-bottom: 1px solid #dedede;
    position: relative;
    line-height:2;
    margin-top: .5rem;
}
.team_r>div h5::after{
    display: block;
    content: "";
    width: 40%;
    height: 2px;
    position: absolute;
    left: 0;
    bottom: -1px;
    background: #d6000f;
}
.team_r>div p{
    font-size: .6rem;
    color: #666;
    margin-top: .2rem;
    line-height: 2;
}
.file h2{
    font-weight: 600;
    font-size: 1rem;
    padding:1rem 0 0rem 0;
    margin-bottom: 1rem;
}
.file{
    font-size: 0;
}
.IT_span .file{
    width: 250px;
    height: 30px;
    position: absolute;
    z-index: 2010000;
    cursor: pointer;
}
.wendangUpload input{
    opacity: 0;
}
.btn-seo::after{
    content: "鍚嶇О";
    display: block;
    color: #666;
    font-size: .7rem;
    margin-top: 50px;
}
.btn-del::after{
    content: "鍒犻櫎";
    display: inline-block;
    padding:.2rem .5rem;
    color: #666;
    font-size: .7rem;
    margin-top: 140px;
    border-radius: 5px;
    box-sizing: border-box;
    transform: translateX(-.5rem);
    border:1px solid #dedede;
    cursor: pointer;
}
.upload_picture{
    position: relative;
}
.unkownfile{
    position: relative;
    width: 300px;
    transform: translateY(-30px);
    height: 50px;
    background: #fff;
    z-index: 222;
}
.unkownfile img{
    height: 34px!important;
    height: auto;
}
.img-list-action{
    position: relative;
    z-index: 20000;
}
.fujian_jianli .img-list-action .btn-del::after{
    margin-top: 0;
    transform: translate(-1rem,-2rem);
    padding:0;
    padding:.1rem .2rem;
}
.upload_picture .file{
    position: absolute;
    left: 0;
    top: 50%;
    transform: translateY(-50%);
    width: 125px;
    height: 176px;
    cursor: pointer;
}
.upload_picture input{
    opacity: 0;
}
.file_l{
    width: 100%;
    margin-right: 4%;
    vertical-align: top;
    display: inline-block;
}
.fancybox-img{
    display: inline-block;
    height: 90%;
}
.fancybox-img img{
    width: 98%;
    height: 96%;
    vertical-align: top;
}
.file_r{
    width: 48%;
    vertical-align: top;
    display: inline-block;
}
.file_l .search_list .search_words,.file_r .search_list .search_words{
    width: 78%;
    padding-left: 4%;
}
.file_l .search_list{
    padding:1rem 0;
    box-sizing: border-box;
    transition: .5s;
    margin-bottom: 0;
}
.file_l  .search_more,.file_r  .search_more{
    padding-right: .2rem;
}
.file_l  .search_list:hover .search_more,.file_r  .search_list:hover .search_more{
    padding-right: .8rem;
}
.file_l .search_list:hover,.file_r .search_list:hover {
    background: #f5f5f5;
}
.file_l .zhangcheng{
    display: inline-block!important;
    width: 48%;
    vertical-align: middle;
    margin-right: 4%;
    border-bottom: 1px solid #f5f5f5;
    border-top: 1px solid #f5f5f5;
}
.file_l .zhangcheng:nth-of-type(2n){
    margin-right: 0;
}

.file_box{
    margin-bottom: 2rem;
}
.file_con{
    padding-bottom: 3rem;
}
.file_more{
    text-align: center;
    font-size: 0;
}
.file_more a{
    display: inline-block;
    width: 150px;
    height: 2rem;
    line-height: 2rem;
    background: #f5f5f5;
    color: #666;
    font-size: .7rem;
    transition: .5s;
}
.file_more a:hover {
    background: #d6000f;
    color: #fff;
}
.allmap{
    width:100%;
    height: 300px;
    position: relative;
    border:1px solid #dedede;
    margin-bottom: 2rem;
}
.contact_con{
    padding:2rem 0 0 0 ;
}
.contact_con h2{
    font-size: 1rem;
    color: #404040;
    font-weight: 600;
    margin-bottom: 1.5rem;
}
.contact_info{
    font-size: 0;
}
.contact_info>div{
    display: inline-block;
    vertical-align: top;
    width: 25%;
    margin-bottom: 2.5rem;
}
.contact_info>div h3{
    color: #666;
    font-size: .8rem;
    font-weight: 600;
    margin-bottom: .7rem;
}
.contact_info>div p{
    font-size: .7rem;
    color: #666;
    position: relative;
    box-sizing: border-box;
    padding-bottom: 1rem;
}
.contact_info>div p::after{
    content: "";
    display: block;
    position: absolute;
    left: 0;
    height: 1px;
    background: #dedede;
    width: 75%;
    bottom: 0rem;
}
.contact_info>div p::before{
    content:"";
    display: block;
    position: absolute;
    left: 0;
    bottom: 0;
    height: 3px;
    width: 20%;
    background: #d6000f;
}
.intro_l{
    padding-top: 0!important;
}
.intro_l h2{
    color: #404040;
    font-size: 1rem;
    font-weight: 600;
}
.intro_l h3{
    color: #999;
    font-size: .8rem;
    line-height: 2;
    margin-bottom: 1.75rem;
}
.intro_con{
    box-sizing: border-box;
    padding:2rem 0 0 0;
}
.intro_l>div{
    font-size: 0;
}
.intro_l>div p{
    display: inline-block;
    vertical-align: top;
    width: 33.3%;
    font-size: .7rem;
    color: #999;
    margin-bottom: 1.6rem;
    text-align: center;
}
.intro_l>div p em{
    display:block;
    width: 100%;
    text-align: center;
    vertical-align: middle;
    margin-right: 3%;
    line-height: 1.5;
}
.intro_l>div p span{
    font-size: 1.5rem;
    /*display: block;*/
    vertical-align: middle;
    /*margin-right: 1%;*/
    color: #d6000f;
}
.intro_l>p{
    color: #666;
    font-size: .7rem;
    line-height: 1.8;
    text-indent: 1.4rem;
    margin-bottom: .5rem;
    text-align: justify;
}
.intro_ewm{
    font-size: 0;
    margin-top: 2rem;
    margin-bottom: 3rem;
}
.intro_ewm p{
    display: inline-block;
    vertical-align: middle;
    width: 50%;
    text-align: center;
}
.intro_ewm p span{
    display: inline-block;
    vertical-align: middle;
    color: #666!important;
    font-size: .7rem!important;
    box-sizing: border-box;
    padding-right:1.5rem;
}
.intro_ewm p i{
    display: inline-block;
    vertical-align: middle;
}
.intro_ewm p:nth-of-type(2){
    text-align: left;
    box-sizing: border-box;
}
.intro_r{
    padding-top: 0!important;
}
.intro_r>a{
    margin-bottom: 2rem;
    display: block;
}
.save_l img{
    width: 100%;
    vertical-align: top;
    margin-bottom: 2rem;
}
.save_l h3{
    font-weight: 600;
    font-size: .9rem;
    color: #404040;
    position: relative;
    margin-bottom: 2rem;
    padding:.1rem 0 .1rem .5rem;
    box-sizing: border-box;
}
.save_l h3::after{
    content: "";
    display: block;
    position: absolute;
    left: 0;
    width: 3px;
    height: 100%;
    background: #d6000f;
    top:50%;
    transform: translateY(-50%);
}
.save_l p{
    color: #777777;
    font-size: .7rem;
    line-height: 2;
}
.save_apply{
    margin-top: 2rem;
    display: inline-block;
    background: #d6000f;
    width: 100px;
    line-height: 1.5rem;
    height: 1.5rem;
    text-align: center;
    color: #fff;
    font-size: .7rem;
    margin-bottom: 3rem;
}
.communication_con>.container1800{
    background: #f5f5f5;
    padding:2rem 0;
}
.communication_con>.container1800>.container{
    background: #fff;
    box-sizing: border-box;
    padding:1rem 1.5rem 2rem 1.5rem;
}
.communication_con>.container1800>.container h2{
    margin-bottom: 1.5rem;
    font-size: 1rem;
    font-weight: 600;

}
.form1{
    font-size: 0;
}
.comm_l{
    width: 34%;
    display: inline-block;
    vertical-align: top;
}
.comm_l p{
    width: 100%;
    position: relative;
    margin-bottom: 1.5rem;
}
.comm_l p input{
    display: block;
    height: 50px;
    box-sizing: border-box;
    padding:.5rem 1.5rem;
    border:1px solid #dedede;
    font-size: .8rem;
    color: #999;
    width: 100%;
}
.comm_l p sup{
    color: #d6000f;
    font-size: 1rem;
    position: absolute;
    left: 1rem;
    top: 50%;
    transform: translateY(-50%);
}
.comm_l p input::-webkit-input-placeholder {
    /* placeholder棰滆壊  */
    color: #999;
    /* placeholder瀛椾綋澶у皬  */
    font-size: .8rem;
}
.comm_r{
    padding-left: 2rem;
    box-sizing: border-box;
}
.comm_r{
    display: inline-block;
    vertical-align: top;
    position: relative;
    width: 66%;
}
.comm_r textarea{
    border:1px solid #dedede;
    padding:.8rem 1.5rem;
    box-sizing: border-box;
    resize: none;
    display: block;
    height: 200px;
    width: 100%;
    font-size: .8rem;
    color: #999;
    margin-bottom: 1rem;
}
.comm_r>sup{
    color: #d6000f;
    font-size: 1rem;
    position: absolute;
    left: 3rem;
    top: .8rem
}
.comm_r label,.comm_r span{
    font-size:.7rem;
    color: #999;
    vertical-align: middle;
}
.checkIcon{
    display: inline-block;
    margin:0 .2rem;
    border:1px solid #ddd;
}
.comm_r input[type='radio'] {
    width: 14px;
    height: 14px;
    background-color: #fff;
    -webkit-appearance: none;
    border: 1px solid #ddd;
    outline: none;
    vertical-align: middle;
}

.comm_r input[type=radio]:checked {
    background: url('../img/comm_yes.png')no-repeat center;
    background-size: 100%;
}
.comm_r>div{
    position: relative;
    padding-left: .5rem;
    box-sizing: border-box;
    line-height: 1.5;
}
.comm_r>div sup{
    font-size: 1rem;
    color: #d6000f;
    position: absolute;
    left: 0;
    top: 0;
}
#canvas{
    width: 30%;
    height: 1.5rem;
    display: inline-block;
    position: absolute;
    right: 0;
    top: 50%;
    transform: translateY(-50%);
    right:.5rem;
}
.btn{
    text-align: center;
    display: inline-block;
    width: 80px;
    height: 1.5rem;
    line-height: 1.5rem;
    background: #d6000f;
    color: #fff;
    border:1px solid #d6000f;
    margin-top: 1.1rem;
    font-size: .7rem;
    cursor: pointer;
    outline: none;
}
.index_about{
    font-size: 0;
    box-sizing: border-box;
    padding:2.5rem 0;
}
.index_about>div{
    display: inline-block;
    vertical-align: top;
}
.index_about .index_about_l{
    width: 47%;
}
.index_about_more{
    color: #d6000f;
    font-size: .7rem;
    display: inline-block;
    vertical-align: middle;
    margin-left: .5rem;
}
.index_about_l h2{
    color: #404040;
    font-size: 1.1rem;
    margin-bottom: .6rem;
    font-weight: 600;
    padding-top: .3rem;
    line-height: 28px;
    overflow: hidden;
}
.index_about_l p{
    color: #666;
    font-size: .7rem;
    text-align: justify;
    line-height: 1.8;
}
.index_about_m{
    width: 34%;
    box-sizing: border-box;
    padding-left: 3.5%;
}
.index_about_tab{
    font-size: 0;
    border-bottom: 1px solid #dedede;
    height: 30px;
    line-height: 30px;
}
.index_about_tab a{
    display: inline-block;
    vertical-align: middle;
    color: #5b5b5b;
    font-size: .8rem;
    font-weight: 600;
    width: 25%;
}
.index_about_tab a p{
    display: inline-block;
    vertical-align: top;
    position: relative;
}
.index_about_tab a p::after{
    content: '';
    display:block;
    width: 0%;
    height: 2px;
    background: #d6000f;
    position: absolute;
    left: 50%;
    bottom: 0;
    transform: translateX(-50%);
    transition: .5s;
}
.index_about_tab a:hover p::after{
    width: 100%;
}

.index_about_tab a.active_a p::after{
    content: '';
    display:block;
    width: 100%;
    height: 2px;
    background: #d6000f;
    position: absolute;
    left: 50%;
    bottom: 0;
}
.index_about_changebox{
    box-sizing: border-box;
    padding-top: .6rem;
}
.index_about_changebox>div{
    display: none;
}
.index_about_changebox>div:nth-of-type(1){
    display: block;
}
.index_about_changebox>div>a{
    display: block;
    width: 100%;
}
.index_about_changebox p{
    color: #666;
    font-size: .7rem;
    line-height: 1.7;
    width: 90%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.index_about_changebox p span{
    display: inline;
    vertical-align: middle;
    margin-right: .2rem;
}
.index_about_r{
    width: 19%;
    box-sizing: border-box;
    padding-left: 3%;
}
.index_about_r img{
    width: 100%;
    vertical-align: top;
}
.social_con>p{
    background: #f5f5f5;
    line-height: 40px;
    height: 40px;
    width: 100%;
    font-size: 0;
    box-sizing: border-box;
    padding-left: .5rem;
}
.social_con>p span{
    display: inline-block;
    vertical-align: middle;
    width: 30%;
    font-size: .8rem;
    color: #404040;
    font-weight: 600;
}
.social_box{
    width: 100%;
    font-size: 0;
}
.social_show{
    cursor: pointer;

}
.social_box .social_show>p {
    font-size: 0;
    height: 60px;
    line-height: 60px;
    transition: .3s;
    border-bottom: 1px solid #dedede;
}
.social_box .social_show>p span{
    display: inline-block;
    vertical-align: middle;
    font-size: .8rem;
    color: #404040;
    width: 30%;
    padding-left: .3rem;
    box-sizing: border-box;
}
.social_box .social_show .bottom_line_p.active_b{
    border-bottom:1px solid transparent;
}
.social_box .social_show>p span:nth-of-type(1){
    box-sizing: border-box;
    padding-left: .5rem;
    font-weight: 600;
    color: #d6000f;
    font-size: .8rem;
}
.down{
    display: inline-block;
    width: 12px;
    height: 12px;
    vertical-align: middle;
    transform: translateY(.2rem);
    transition: .3s;
    transform: rotate(180deg);
}
.down.active_down{
    transform: rotate(0deg);
}
.down img{
    width: 100%;
    vertical-align: top;
}
.social_hid{
    width: 95%;
    border:1px solid #dedede;
    padding:1.5rem;
    box-sizing: border-box;
    position: relative;
    display: none;
}
.social_hid::before{
    content: "";
    width: 0px;
    height: 0px;
    border-left: 8px solid transparent;
    border-right: 8px solid transparent;
    border-bottom: 8px solid #dedede;
    position: absolute;
    display: block;
    top: -8px;
    z-index: 10;
    left: 5%;
    transition: .5s;
}
.social_hid::after{
    content: "";
    width: 0px;
    height: 0px;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    border-bottom: 10px solid #fff;
    position: absolute;
    display: block;
    top: -6px;
    z-index: 121;
    left: 4.75%;
    transition: .5s;
}
.social_hid>div>h2{
    font-size: .8rem;
    color: #404040;
    font-weight: 600;
    margin-bottom:.5rem;
}
.social_hid>div:nth-of-type(1){
    margin-bottom: 1.5rem;
}
.submit_file{
    display: inline-block;
    width: 100px;
    height: 1.5rem;
    line-height: 1.5rem;
    text-align: center;
    color: #fff;
    font-size: .7rem;
    background: #d6000f;
    margin-top: 1rem;
}
.social_more{
    text-align: center;
    margin:2rem 0 3rem 0;
}
.social_more a{
    width: 150px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    font-size: .7rem;
    color: #666;
    display: inline-block;
    background: #f5f5f5;
    transition: .5s;
}
.social_more a:hover{
    color: #fff;
    background: #d6000f;
}
.campusFile_l>h2{
    font-size: .9rem;
    font-weight: 600;
    color: #404040;
    text-align: center;
    margin-bottom: .5rem;
}
.acmpusFile_form{
    width: 100%;
}
.acmpusFile_form>h3{
    color: #404040;
    font-size: .8rem;
    font-weight: 600;
    box-sizing: border-box;
    padding-left: .5rem;
    position: relative;
    height: 22px;
    line-height: 22px;
    margin-bottom: 1rem;
}
.acmpusFile_form>h3::after{
    content: "";
    display: block;
    position: absolute;
    left: 0;
    top: 50%;
    transform: translateY(-50%);
    width: 3px;
    height: 100%;
    background: #d6000f;
}
.key4 sup{
    opacity: 0;
}
.upload_picture{
    background: transparent;
    border:0px solid transparent;
    cursor: pointer;
}
.jiben{
    font-size: 0;
    margin-bottom: 1.4rem;
}
.jiben .jiben_l{
    display: inline-block;
    vertical-align: top;
    width: 85%;
    box-sizing: border-box;
}
.jiben .jiben_l>div{
    border-bottom: 1px solid #dedede;
    border-right: 1px solid #dedede;
    height: 34px;
}
.jiben .jiben_l>div:nth-of-type(1){
    border-top: 1px solid #dedede;
}
.jiben .jiben_l span{
    display: inline-block;
    width: 16.5%;
    padding:.5rem 0;
    height: 100%;
    box-sizing: border-box;
    text-align: center;
    font-size: .7rem;
    color: #404040;
    color: #999;
    vertical-align: middle;
    border-left:1px solid #dedede;
}
.jiben .jiben_l span>sup,.jianli span sup,.jiben_b span sup,.jiben_r div span sup{
    color: #d6000f;
    font-size: 1rem;
    display: inline-block;
    margin-right: .1rem;
    vertical-align: middle;
}
.file_bac{
    background: #f5f5f5;
}
.span3{
    width: 50%!important;
}
.span5{
    width: 82.5%!important;
}
.jiben  span select,.jiben_b  span select{
    appearance:none;
    -moz-appearance:none;
    -webkit-appearance:none;
    border:1px solid transparent;
    width: 100%;
    display: inline-block;
    background: url('../img/file_arrow.png')no-repeat 92%;
    padding-left: 42%;
}
.jiben_b  span select{
    background: url('../img/file_arrow.png')no-repeat 94%;
    color: #999;
    line-height: 1.5;
}
/*娓呴櫎ie鐨勯粯璁ら€夋嫨妗嗘牱寮忔竻闄わ紝闅愯棌涓嬫媺绠ご*/
.jiben .jiben_l span select::-ms-expand { display: none;
}
.jiben .jiben_b span select::-ms-expand { display: none;
}
.select_hy{
    padding-left: 34%!important;
}
.select_shuoshi{
    padding-left: 20%!important;
}
.jiben_r{
    display: inline-block;
    width: 15%;
    border-top: 1px solid #dedede;
    border-right: 1px solid #dedede;
    border-bottom: 1px solid #dedede;
    box-sizing: border-box;
    vertical-align: top;
    position: relative;
    height: 176px;
}
.assess{
    border-top: 0px solid transparent!important;
}
.jiben_r div{
    position: absolute;
    left: 0;
    width: 100%;
    top: 50%;
    font-size: .7rem;
    text-align: center;
    transform: translateY(-50%);
}

.jiben_r div img{
    margin-bottom: .5rem;
}
.jiben_r div span{
    display: block;
    color: #999;
    font-size: .7rem;
}
.jiben_b{
    width: 100%;
    height: 34px;
    border-left: 1px solid #dedede;
    border-bottom: 1px solid #dedede;
    box-sizing: border-box;
}
.jiben_b span{
    display: inline-block;
    vertical-align: middle;
    width: 24.88%;
    font-size: .7rem;
    color: #999;
    text-align: center;
    height: 100%;
    line-height: 34px;
    border-right: 1px solid #dedede;
}
.birth,.shuoshi,.benke,.shijian_date{
    width: 100%;
    background: url('../img/calendar.png')no-repeat 16px;
    color: #999;
    border:1px solid transparent;
    box-sizing: border-box;
    padding-left: 2rem;
}
.jiaoyu{
    border-top: 1px solid #dedede;
    margin-bottom: 1.5rem;
}
.panduan sup{
    opacity: 0;
}
.language{
    margin-bottom: 1.5rem;
}
.language_item_con{
    font-size: 0;
    border-right: 1px solid #dedede;
    border-top: 1px solid #dedede;
    border-bottom: 1px solid #dedede;
}
.language_item_con span{
    display: inline-block;
    vertical-align: middle;
    width: 35.8%;
    font-size: .7rem;
    color: #999;
    /*height: 34px;*/
    height: 31px;
    line-height: 34px;
    box-sizing: border-box;
    text-align: center;
    border-left: 1px solid #dedede;
}
.language_item_con .language_item{
    width: 14.1%;
}
.IT_span{
    width: 82.5%!important;
}
.IT_span textarea{
    color: #666;
    font-size: .7rem;
    width: 100%;
    height: 100%;
    resize: none;
    box-sizing: border-box;
    border:0px solid transparent;
}
.hobby{
    height: 100px;
    width: 99.8%;
}
.hobby span{
    height: 100px;
    line-height: 1.5;
}
.hobby_span{
    padding-top: 40px;
}
.hobby_span2{
    padding:.5rem;
    box-sizing: border-box;
    text-align: left!important;
    overflow: hidden;
}
.shijian span{
    display: inline-block;
    vertical-align: middle;
    text-align: center;
    height: 34px;
    line-height: 34px;
    font-size: .7rem;
    color: #999;
    width: 16.5%;
    border-left: 1px solid #dedede;
}
.shijian{
    font-size: 0;
    border-top: 1px solid #dedede;
    border-right: 1px solid #dedede;
}
.jianli{
    font-size: 0;
    height: 66px;
    border-bottom: 1px solid #dedede;
    border-right: 1px solid #dedede;
}
.jianli_l{
    display: inline-block;
    width: 14.2%;
    vertical-align: top;
    border-left: 1px solid #dedede;
    font-size: .7rem;
    color: #999;
    height: 66px;
    text-align: center;
    padding-top: 30px;
    box-sizing: border-box;
}
.jianli_r{
    display: inline-block;
    width: 85%!important;
    vertical-align: top;
    border-left: 1px solid #dedede;
    font-size: .7rem;
    color: #999;
    height: 66px;
    text-align: left;
    box-sizing: border-box;
    padding:1rem;
}
.jianli_l1{
    padding-top: 20px!important;
}
.jianli_l2{
    margin-bottom: 26px!important;
}
.acmpusFile_form>p>sup{
    color: #d6000f;
    font-size: 1rem;
}
.acmpusFile_form>p{
    font-size: .7rem;
    color: #999;
    margin-bottom: 2rem;
}
.other{
    margin-bottom: .5rem!important;
}
.campus_btn{
    text-align-last: center;
    margin-bottom: 3rem;
}
.campus_btn button{
    border:1px solid #d6000f;
    background: #d6000f;
    color: #fff;
    width: 80px;
    height: 30px;
    display: inline-block;
    text-align: center;
    font-size: .7rem;
    line-height: 30px;
    cursor: pointer;
}
.socialFile_nianxin span{
    width: 25%!important;
}
.file_borderTop{
    border-top: 1px solid #dedede;
}
.zhengshu{
    border-top: 1px solid #dedede;
    margin-bottom: 1.5rem;
}
.zhengshu .fancybox-img img{
    width: 54px;
    height: 96%;
}
.zhengshu .img-list{
    transform: translateY(-1.8rem);
    background: #fff;
    height: 3.2rem;
}
.zhengshu .img-list-action .btn-del::after{
    color: #fff;
}
.mm_org,.m_nav{
    display: none;
}
.key input,.key3 input,.key1 input{
    width: 98%;
    color: #666;
    font-size: .7rem;
    text-align: left;
    border:1px solid transparent;
    outline: none;
    box-sizing: border-box;
    padding-left: .5rem;
}
.shijian_more{
    font-size: 1rem;
    color: #999;
    display: block;
    text-align: right;
    box-sizing: border-box;
    padding-right: .5rem;
    transform: translateY(-1rem);
}
.shijian_less{
    font-size: 1rem;
    color: #999;
    display: block;
    text-align: right;
    box-sizing: border-box;
    margin-bottom: .5rem;
}
.shijian_less a{
    display: inline-block;
    vertical-align: middle;
    width: 20px;
    height: 20px;
    text-align: center;
    line-height: 17px;
    background: #f5f5f5;
    color: #999;
    border-radius: 50%;
    box-sizing: border-box;
}
.show_tab{
    border-bottom: 1px solid #dedede;
    height: 40px;
    box-sizing: border-box;
    margin-bottom: 1rem;
}
.show_tab a{
    display: inline-block;
    vertical-align: middle;
    width: 120px;
    text-align: center;
    height: 40px;
    line-height: 40px;
    color: #999;
    font-size: .8rem;
    font-weight: 600;
    margin-right: 1rem;
    background: #f9f9f9;
    transition: .5s;
}
.show_tab a:hover {
    color: #fff;
    background: #d6000f;
}
.show_tab a.active_a{
    color: #fff;
    background: #d6000f;
}
.mmintro_ewm{
    display: none;
}
.normal input{
    width: 100%;
    border:0px solid transparent;
    outline: none;
    color: #666;
    font-size: .7rem;
}
#photo{
    width: 100%;
    height: 100%;
}
.textarea_box_l{
    width: 14.1%!important;
    box-sizing: border-box;
}
.emailPhone_box span{
    width: 25%!important;
}
.business_swiper_con .swiper-slide>a{
    display: block;
    width: 100%;
    height: 100%;
}
.business_swiper_con .swiper-slide>a img{
    width: 100%;
    vertical-align: top;
}
.business_swiper_con .swiper-pagination{
    bottom: 50px!important;
    text-align: right!important;
    padding-right: 1%;
    box-sizing: border-box;

}
.business_swiper_con .swiper-pagination-bullet{
    border: 1px solid #fff;
    border-radius: 50%!important;
    background: rgba(255,255,255,0)!important;
    transition: .3s;
    width: 6px;
    height: 6px;
    opacity: 1!important;
}
.business_swiper_con .swiper-pagination-bullet-active{
    background: rgba(255,255,255,1)!important;
}
.business_intro{
    line-height: 1.8;
    color: #777;
    text-indent: 1.4rem;
    font-size: .7rem;
    text-align: justify;
    padding-bottom: 1.4rem;
    margin-bottom: 1rem;
    box-sizing: border-box;
}
.business_intro p:last-child{
    text-indent: 0;
}
.business_intro img{
    max-width: 100%;
}
.mmShoufengqin,.mm_socialFile,.index_mm,.mm_guben,.mm_busBanner{
    display: none;
}
.m_yt,.mmfooter{
    display: none!important;
}
@media screen and (max-width:1200px){
.newsDetail_con img{
        height: auto!important;
    }
.social_box .social_show>p{
        height: auto;
        padding:.5rem;
        line-height: 1.4;
        padding-left: 0;
    }
.mm_business_tab{
        width: 100%;
        font-size: 0;
        margin-bottom: 1rem;
    }
.mm_business_tab a{
        display: inline-block;
        vertical-align: middle;
        font-size: .7rem;
        background:#f9f9f9;
        color: #666;
        text-align: center;
        box-sizing: border-box;
        width: 30%;
        margin-right: 3.33%;
        margin-bottom: .5rem;
        padding:.5rem .5rem;
        box-shadow: .2rem -.2rem 0 rgba(204,204,204,.3);
    }
.mm_business_tab a:nth-of-type(3n){
        margin-right: 0;
    }
.index_about_l h2{
        font-size: .9rem;
    }
.mm_intro_con .container .intro_l{
        padding-left: 0!important;
    }
.mm_sousuo{
        width: 19px;
        height: 19px;
        float:right;
        margin-right: 3rem;
        margin-top: 1.1rem;
        display: inline-block;
        background:url('../img/search.png')no-repeat center;
    }
.m_logo em{
        font-size: .9rem;
        color: #666;
        float: right;
        margin-top: 1.2rem;
        margin-right: .8rem;
    }
.m_logo em a{
        color: #666;
    }
.mm_sousuo_cover{
        box-sizing: border-box;
        width: 100vw;
        height: 100vh;
        background: #fff;
        padding:2rem;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 2000000;
        display: none;
    }
.mm_sousuo_cover p{
        margin-bottom: 4rem;
    }
.mm_sousuo_cover form{
        width: 100%;
        text-align: center;
        position: relative;

    }
.mm_sousuo_cover em{
        font-size: 2rem;
        color: #000;
        position: absolute;
        right: 1rem;
        top: 1.5rem;
        z-index: 2;
    }
.mm_sousuo_cover form input{
        width:100%;
        height: 40px;
        box-sizing: border-box;
        padding:.5rem 1rem;
        outline: none;
        border:none;
        border-bottom: 1px solid #000;
    }
.mm_sousuo_cover form .mm_btn{
        display: inline-block;
        vertical-align: middle;
        width: 19px;
        height: 19px;
        background: url('../img/search.png')no-repeat center;
        outline: none;
        border: none;
        cursor: pointer;
        position: absolute;
        right: 0;
        top: 50%;
        transform: translateY(-50%);
    }
.m7 h2,.m3 h2,.m4 h2,.m5 h2,.m6 h2,.m7 em,.m3 em,.m4 em,.m5 em,.m6 em{
        color: #fff!important;
    }
.m2 h2,.m2 em{
        color: #666!important;
    }
.crumbs,.bus_sec_nav{
        display: none;
    }
.banner>.container1800>.container{
        left: 37%;
    }
.banner>.container1800>.container img{
        width: 66%;
    }
.pc_guben,.pc_busBanner{
        display: none;
    }
.mm_guben,.mm_busBanner{
        display: block;
    }
.index_cover{
        width: 72%;
        left: 28%;
    }
.mm_index_cover_img{
        margin-top: 2rem;
    }
.index_carousel_con .swiper-slide>a>video{
        width: auto;
        height: 100%;
    }
.index_carousel .swiper-button-next{
        right: 2%;
        outline: none;
    }
.index_carousel .swiper-button-prev{
        left: 2%;
        outline: none;
    }
.index_carousel{
        padding-bottom: 53%;
    }
.index_pc{
        display: none;
    }
.index_mm{
        display: block;
    }
.company_otherList .search_words{
        width: 79%;
    }
.pcfooter{
        display:none;
    }
.footer_bottom{
        height: auto;
    }
.mmfooter{
        display: block!important;
    }
.IT_span{
        width: 82%!important;
    }
.xm_name{
        width: 25.8%!important;
    }
.span18{
        width: 18%!important;
    }
.borderTop{
        border-top: 1px solid #dedede!important;
    }
.jianli,.jianli_l,.jianli_r{
        height: 100px;
        line-height: 1.5;
    }
.upload_picture .file{
        width: 105px;
        left: -20px;
    }
.mm_guben_con{
        padding:2rem 1rem 2rem 1rem;
    }
.structure img{
        width: 100%;
        vertical-align: top;
    }
.mm_intro_con{
        padding-top: 2rem!important;
    }
.language_item_con{
        border-top: 0 solid transparent;
    }
.shijian{
        border-right: 0 solid transparent;
    }
.shijian span{
        border-left: 0 solid transparent;
    }
.jiben_b span{
        box-sizing: border-box;
    }
.jiaoyu .jiben_b>span{
        width: 50%;
        border-bottom: 1px solid #dedede;
    }
.jiben_b{
        height: auto;
        border-bottom: 0px solid transparent;
    }
.jiben_b>div{
        border-bottom: 1px solid #dedede;
    }
.jiben_b>div:nth-child(1)>span:nth-of-type(1){
        width: 24.88%;
    }
.jiben_b>div>span:nth-of-type(1),.language .jiben_b>div>span:nth-of-type(1){
        width: 50%;
    }
.jiben_b>div>span:nth-of-type(2){
        width: 50%;
    }
.m_span{
        width: 75%!important;
    }
#m_birth{
        width: 75%;
    }
.jiben_r{
        width: 30%;
    }
.jiben_l{
        width: 70%!important;
    }
.jiben .jiben_l span{
        width: 25%;
        font-size: .6rem;
    }
.mm_socialFile{
        display: block;
    }
.foot_t_r{
        float: left!important;
        padding-left: .4rem;
    }
.m_yt{
        display: block!important;
    }
.file_l .zhangcheng{
        width: 100%;
    }
.show_tab{
        height: 31px;
    }
.show_tab a{
        width: 100px;
        height: 30px;
        line-height: 30px;
        margin-right: .5rem;
        box-sizing: border-box;
    }
.index_about{
        padding:2rem 0;
    }
.m_yt{
        box-sizing: border-box;
        padding:0 1rem;
        width: 100%;
        margin-bottom: 1rem;
    }
.m_yt img{
        width: 100%;
        vertical-align: top;
    }
.index_about .index_about_l{
        width: 100%;
        padding-left: 1rem;
        padding-right: 1rem;
        box-sizing: border-box;
        margin-bottom: 1rem;
    }
.index_about_r{
        display: none!important;
    }
.index_about_m{
        width: 100%;
        box-sizing: border-box;
        padding-right: 1rem;
        padding-left: 1rem!important;
    }
.container750{
        margin:0 auto;
        padding:0 1rem;
    }
.sfq_div{
        margin-bottom: 1rem;
    }
.sfq_div>a{
        display: block;
        width: 100%;
        height: 100%;
        position: relative;
    }
.sfq_div>a>img{
        width: 100%;
    }
.sfq_div>a>div{
        position: absolute;
        top: 0;
        left: 0;
        z-index: 20;
        box-sizing: border-box;
        padding:1rem;
    }
.sfq_div>a>div h2{
        color: #333;
        font-size: .9rem;
        margin-bottom: .5rem;
    }
.sfq_div>a>div img{
        margin-bottom: .5rem;
    }
.sfq_div>a>div em{
        color: #666;
        font-size: .7rem;
    }
.mmShoufengqin{
        display: block;
    }
.mmintro_ewm{
        display: block;
    }
.pc_org,.shoufenqin,.pc_socialFile{
        display: none;
    }
.mm_org,.m_nav{
        display: block;
    }
.header,.intro_r{
        display: none;
    }
.search_words{
        width: 77%;
    }
.intro_r{
        overflow: hidden;
        display: none!important;
    }
.second_ul{
        width: 100%;
        padding:0 1rem;
        box-sizing: border-box;
    }
.second_ul li{
        margin-right: 1rem;
    }
.intro_con {
        padding:0rem 1rem 0 1rem;
    }
.banner>.container1800>img{
        width: 100%;
    }
.intro_l>div p span{
        font-size: 1rem;
    }
.intro_l{
        width: 100%!important;
        padding-right: 0!important;
        border-right: 0 solid transparent!important;
    }
.intro_l>div p{
        display: block;
        width: 100%;
    }
.intro_l>div .mm_intro em{
        width:100%;
        margin-right: 2%;
    }
.intro_ewm{
        display: none;
    }
.mmintro_ewm{
        margin-top: 1rem;
    }
.mmintro_ewm p{
        width:50%!important;
        display: inline-block!important;
        text-align: center!important;
    }
.mmintro_ewm p span{
        margin-bottom: .5rem;
        color: #666!important;
        font-size: .7rem!important;
        text-align: center;
        display: block!important;
    }
.footer_b_l{
        width: 100%;
        text-align: center;
        margin-bottom: 1rem;
    }
.foot_b_r {
        display: none;
    }
.banner{
        overflow: hidden;
    }
.contact_con,.run_con{
        padding:2rem 1rem 0 1rem;
    }
.business_con>.container .business_r{
        display: none;
    }
.business_con>.container .business_l{
        width: 100%;
        padding:2rem 1rem 0 1rem;
        border-right: 0px solid transparent;
    }
.comm_l{
        width: 100%;
    }
.comm_r{
        padding-left: 0;
        width: 100%;
        text-align: center;
    }
.comm_r>sup{
        left: 1rem;
    }
.comm_r>div{
        text-align: left;
    }
.contact_info>div{
        width: 100%;
    }
.file_l,.file_r{
        width: 100%;
    }
.file_l .search_list .search_words, .file_r .search_list .search_words{
        width: 73%;
    }
.file h2{
        padding:1rem 0 0 0;
    }
.team_l{
        width: 100%;
    }
.team{
        padding:2rem 1rem 1rem 1rem;
    }
.team_r{
        width: 100%;
        padding-left: 0;
        margin-top: 1rem;
    }
.Bigleader_intro{
        margin-top: -20%;
        padding-left: 14%;
    }
.social_hid::after{
        left: 4.5%;
    }
.company_l{
        width: 100%;
    }
.company_r{
        padding-left: 0;
        width: 100%;
        margin-top: 1rem;
    }
.company_otherList .search_words>div p{
        max-height: 46px;
    }
    /*.company_otherList .search_words{
        width: 81%;
    }*/
.newsDetail_con,.newsDetail_page{
        width: 97%;
    }
.newsDetail_page>a{
        width: 100%;
        margin-bottom: 1rem;
    }
.newsDetail .search_words{
        width: 75.6%;
        padding-bottom: .5rem;
    }
.newsDetail h2{
        line-height: 1.5;
    }
.org h1{
        padding-left: 1rem;
        box-sizing: border-box;
    }
    /*mm header*/
.m_nav {
        display: block;
        position: relative;
        width: 100%;
        box-sizing: border-box;
        z-index: 200000;
        margin-top: 0;
        background: #fff;
        height: 60px;
        margin-bottom: 0;
        padding-left: 0;
    }
.m_logo img {
        width: auto;
        margin: 1rem .5rem 0;
    }
.m_nav .menu_p em {
        position: absolute;
        top: 50%;
        right: 10px;
        transform: translate(-20%,-50%);
        width: 1rem;
        font-size: 0;
    }
.m_nav .menu_p i {
        display: inline-block;
        width: 20px;
        height: 2px;
        background: #898989;
        margin: 3px 0;
        opacity: 1;
        transition: 0.5s;
        -webkit-transition: 0.5s;
    }
    nav {
        display: none;
        position: absolute;
        top: 60px;
        left: 0;
        z-index: 20000;
        width: 100%;
        background: rgba(255,255,255,.85);
    }
    nav .sj_footer {
        width: 100%;
        background: rgba(255,255,255,.85);
        padding: 6% 20px;
        box-sizing: border-box;
    }
    nav .sj_footer .left_div div {
        width: 100%;
        margin: 0;
        cursor: pointer;
    }
    nav .sj_footer strong {
        height: 40px;
        line-height: 40px;
        margin-bottom: 0;
        color: #595757;
        font-weight: 500;
        font-size: 14px;
    }
    nav .sj_footer strong i {
        display: block;
        float: right;
        color: #898989;
        font-size: 20px;
        transition: .3s;
    }
    nav .sj_footer .left_div p {
        display: none;
    }
    nav .sj_footer a {
        width: 90%;
        font-size: 14px;
        color: #898989;
        display: inline-block;
        line-height: 30px;
        transition: 0.5s;
        vertical-align: middle;
        -webkit-transition: 0.5s;
    }
.m_nav .active_p em i:nth-of-type(3) {
        -webkit-transform: rotate(-45deg) translate(9px, -8px);
        transform: rotate(-45deg) translate(5px, -5px);
    }
.m_nav .active_p em i:nth-of-type(1) {
        -webkit-transform: rotate(45deg) translate(6px, 6px);
        transform: rotate(45deg) translate(6px, 6px);
    }
    nav .sj_footer .active_strong i {
        -webkit-transform: rotate(45deg);
        transform: rotate(45deg);
    }
.m_nav .active_p em i:nth-of-type(2) {
        opacity: 0;
    }
.foot_t_l{
        transform: translateY(-2px);
        margin:0 .5rem;
    }
.foot_t_r{
        text-align: center;
        margin-top: 1rem;
    }
.foot_t_r ul{
        line-height: 1.8;
    }
.foot_t_r ul li{
        line-height: 1.8;
        padding:0 .5rem 0 .5rem;
    }
.footer_bottom p{
        line-height: 1.6;
    }
}
.intro_l .intro_ewm p{
    width: 50%;
}