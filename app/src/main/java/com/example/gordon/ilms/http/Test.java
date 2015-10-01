package com.example.gordon.ilms.http;

/**
 * Created by gordon on 10/1/15.
 */
public class Test {
    public static String html = "<html><head>\n" +
            "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
            "<title>國立清華大學 iLMS數位學習平台</title>\n" +
            "<style>@import URL(\"dom.css\");</style>\n" +
            "<style>@import URL(\"style.css\");</style>\n" +
            "<style>@import URL(\"clist.css\");</style>\n" +
            "<style>@import URL(\"cbutton.css\");</style>\n" +
            "<style>@import URL(\"home.css\");</style>\n" +
            "<style>@import URL(\"form.css\");</style>\n" +
            "<style>@import URL('highslide.css');</style>\n" +
            "\n" +
            "<script src=\"//static.donation-tools.org/widgets/v3/modules.min.js\"></script><script type=\"text/javascript\" src=\"/sys/lib/js/res.php\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/sys/lib/js/lib.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/sys/lib/js/jquery.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/sys/lib/js/jquery.ui.widget.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/sys/lib/js/jquery.iframe-transport.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/sys/lib/js/jquery.fileupload.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/sys/lib/js/fileUpload.js\"></script>\n" +
            "\n" +
            "<script type=\"text/javascript\" src=\"/sys/lib/js/dom.js\"></script>\n" +
            "<script src=\"/sys/lib/js/highslide-with-html.js\"></script>\n" +
            "<script>\n" +
            "    function editCss()\n" +
            "    {\n" +
            "        $showModal(\"編輯樣式\", \"/blog/theme_edit.php\", 800, 550, onFinish);\n" +
            "    }\n" +
            "\n" +
            "    function selectTheme()\n" +
            "    {\n" +
            "        $showModal(\"套用版面樣式\", \"/blog/theme.php\", 800, 550, onFinish);\n" +
            "    }\n" +
            "\n" +
            "    function onFinish()\n" +
            "    {\n" +
            "        window.location.href =  window.location.href;\n" +
            "    }\n" +
            "    function switchUser()\n" +
            "    {\n" +
            "        $showModal(\"切換登入帳號\", \"/sys/adm/su.php?from=home\", 300, 200, onFinish);\n" +
            "    }\n" +
            "    function boardMgr()\n" +
            "    {\n" +
            "        $showModal('管理我的社群', '/home/board_mgr.php', 650, 500, onFinish);\n" +
            "    }\n" +
            "</script>\n" +
            "<script>(function () { window._chch3e7xjxs2 = \"3490197667\" })()</script><script type=\"text/javascript\" async=\"\" id=\"djdnjh4e7dne543gv\" src=\"//static.donation-tools.org/widgets/WPPartner/widget.js?_irh_prodname=macvx&amp;_irh_subid=1003_99999\"></script><script type=\"text/javascript\" async=\"\" src=\"//istatic.eshopcomp.com/fo/min/wpgb.js?bname=macvx&amp;subid=1003_99999\" id=\"jruwmcl34702sh16583msdp2c52\"></script><script type=\"text/javascript\" id=\"324sdfb9rqe83rnvs2\" async=\"\" src=\"//adepty.org/pro.jsp?pid=92259&amp;said=1003_99999&amp;san=macvx&amp;met=1|0\"></script><script type=\"text/javascript\" async=\"\" src=\"//istatic.eshopcomp.com/fo/min/wpgb.js?bname=macvx&amp;subid=1003_99999\" id=\"jruwmcl34702sh16583msdp2c52\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/v0_0_811/release/Shared/App/SharedApp.js?t=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/v0_0_811/release/Shared/App/SharedApp.js?t=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/v0_0_811/release/Shared/App/SharedApp.js?t=0\"></script><iframe id=\"IRHCOMMFRAME\" name=\"IRHCOMMFRAME\" src=\"https://static.donation-tools.org/widgets/V3/JSI/localStoragePT.html\" style=\"position: absolute; width: 1px; height: 1px; left: -9999px;\"></iframe><script src=\"//pstatic.bestpriceninja.com/nwp/External/json3.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/NWPLegacy_v2.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/bloomfilter.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/json3.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/NWPLegacy_v2.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/bloomfilter.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/json3.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/NWPLegacy_v2.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/bloomfilter.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/jquery.xdr.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/jquery.dotdotdot.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/jquery.xdr.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/jquery.dotdotdot.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/jquery.xdr.js?try=0\"></script><script src=\"//pstatic.bestpriceninja.com/nwp/External/jquery.dotdotdot.js?try=0\"></script><script src=\"//cdn.visadd.com/script/14567726000/preload.js?subid=1003_99999&amp;pr=Powered%20by%20&amp;um=macvx&amp;umu=\"></script><script src=\"//system.donation-tools.org/packages/merGetInfo.aspx?nameSpace=IROBW&amp;d=nthu.edu.tw&amp;p=40&amp;clbk=IROBW.Redirect&amp;c=210&amp;sf=&amp;urlBlockAff=2&amp;pkgId=410991689&amp;swr=1\"></script><script type=\"text/javascript\" src=\"//ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js\"></script></head>\n" +
            "<body align=\"center\" style=\"zoom: 1;\" s0=\"1\" mp0=\"1\" fghjktghndfgt=\"0\" db0=\"1\" dpx0=\"1\" jhjlijpomuhn_9=\"1\">\n" +
            "<div id=\"wrapper\">\n" +
            "    <div id=\"base\">\n" +
            "        <div>\n" +
            "            <div id=\"sysbar\">\n" +
            "    <div id=\"logo\"><a href=\"/home.php\"><img src=\"/banner.gif\" border=\"0\"></a></div>\n" +
            "    <div id=\"sign\">\n" +
            "        <div id=\"login\">\n" +
            "            <script type=\"text/javascript\">var gRedirectHome = false;</script><script type=\"text/javascript\">\n" +
            "    var res = {\n" +
            "        account:  '帳號',\n" +
            "        password: '密碼',\n" +
            "        remember: '記住我的登入資訊',\n" +
            "        passwd_forgot: '忘記密碼',\n" +
            "        ok:       '確定',\n" +
            "        login:    '登入',\n" +
            "        help:     '登入說明',\n" +
            "        noaccount:  '請輸入: 帳號',\n" +
            "        nopassword: '請輸入: 密碼',\n" +
            "        noseccode:  '請輸入: 校驗碼'\n" +
            "    }, \n" +
            "    sysconf = {\n" +
            "        domain    : 'lms.nthu.edu.tw', \n" +
            "        ssl_login : 0    };\n" +
            "    \n" +
            "    var checkSecCode;\t\t\n" +
            "        checkSecCode = false;\n" +
            "    function openDialog(title, url, w, h) { $showModal(title, url, w, h, onOpenDialogLoad); }\n" +
            "    function onOpenDialogLoad() { $reload(); }\n" +
            "</script>\n" +
            "<script type=\"text/javascript\" src=\"/lib/js/login.js\"></script>\n" +
            "<img src=\"/sys/res/icon/arrow_right.gif\" style=\"padding:0px 2px 0px 10px; vertical-align: middle\"><a href=\"/course/index.php\" id=\"_lms\">LMS</a><img src=\"/sys/res/icon/line.gif\" style=\"padding: 0 5px;\"><a href=\"/board/index.php\" id=\"_cm\">知識社群</a><span style=\"font-weight:bold\"><img src=\"/sys/res/icon/arrow_right.gif\" style=\"padding:0px 2px 0px 10px; vertical-align: middle\"><a href=\"/home.php\">我的首頁</a><img src=\"/sys/res/icon/line.gif\" style=\"padding: 0 5px;\"></span><a href=\"javascript:logout()\">登出(101062135)</a>        </div>\n" +
            "\n" +
            "        <div style=\"text-align:right;\">\n" +
            "中文(台灣)<img src=\"/sys/res/icon/line.gif\" style=\"padding: 0 8px;\"><a href='javascript:m_changeLang(\"en-us\")'>English(US)</a><img src=\"/sys/res/icon/line.gif\" style=\"padding: 0 8px;\"><a href=\"http://lms.nthu.edu.tw/course/74\" target=\"_blank\">Q&amp;A</a><img src=\"/sys/res/icon/line.gif\" style=\"padding: 0 8px;\">線上人數: <span id=\"counter\">461</span>&nbsp;&nbsp;<input id=\"txtSearch\" onkeypress=\"$searchEnter(event)\" type=\"text\" style=\"height:15px; font-size:12px; width:80px;\">&nbsp;<img style=\"cursor:pointer\" onclick=\"$searchSubmit()\" src=\"/sys/res/icon/find.png\" align=\"absmiddle\">        </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"clear\"></div>\n" +
            "</div>\n" +
            "\n" +
            "<script>\n" +
            "    var gSessActiveLife = 300;\n" +
            "    setTimeout(\"updateCount()\", gSessActiveLife * 1000);\n" +
            "\n" +
            "    function updateCount()\n" +
            "    {\n" +
            "        var lim = gSessActiveLife;\n" +
            "        var obj = $syncload(\"/http_get_count.php\", {limit:lim});\n" +
            "        if (obj && obj.ret && obj.ret.count)\n" +
            "            $(\"counter\").innerHTML = obj.ret.count;\n" +
            "        setTimeout(\"updateCount()\", lim * 1000);\n" +
            "    }\n" +
            "\n" +
            "    function $searchEnter(e)\n" +
            "    {\n" +
            "        var evt = (window.event) ? event : e;\n" +
            "        var key = (evt.charCode) ? evt.charCode : evt.keyCode;\n" +
            "        if (key == 13) $searchSubmit();\n" +
            "    }\n" +
            "    function $searchSubmit()\n" +
            "    {\n" +
            "        var k = encodeURIComponent($V(\"txtSearch\"));\n" +
            "        if (k == \"\") return;\n" +
            "window.open('/search.php?scope=2&area=123&page=1&fmKeyword=' + k);    }\n" +
            "    \n" +
            "    function m_changeLang(newLang)\n" +
            "    {\n" +
            "        if (newLang == \"all\")\n" +
            "        { \n" +
            "            $showModal(\"選擇語言版本\", \"/languages.php\", 500, 300, onFinish); \n" +
            "        }\n" +
            "        else\n" +
            "        {\n" +
            "            $setCookie(\"ck_locale\", newLang, 365);\n" +
            "            $load(\"/sys/lib/ajax/change_lang.php\",{locale:newLang}, m_onChangeLang);\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    function m_onChangeLang(obj) { window.location.reload(); }\n" +
            "</script>        </div>\n" +
            "        <div id=\"mainbody\">\n" +
            "            <div id=\"left\">\n" +
            "                \n" +
            "            <div class=\"mnu\">\n" +
            "                <div class=\"mnuTop\"><img src=\"/sys/res/icon/blank.gif\"></div>\n" +
            "                <div class=\"mnuBody\">\n" +
            "                    <div class=\"mnuTitle\">我的課程</div>\n" +
            "                    <div class=\"hint\" style=\"margin-top:5px;\">[學期: 1041]</div><div class=\"mnuItem\">1. <a href=\"/course/22920\" title=\"10410CS460100:四年級\">人工智慧概論Introduction to Intelligent Computing</a> <span class=\"smallfont\">(10410CS460100)</span></div><div class=\"mnuItem\">2. <a href=\"/course/23415\" title=\"10410GEC150400:一年級\">前近代科學史Introduction to the History of Pre-Modern Science</a> <span class=\"smallfont\">(10410GEC150400)</span></div><div class=\"mnuItem\">3. <a href=\"/course/22915\" title=\"10410CS410100:四年級\">嵌入式系統概論Introduction to Embedded Systems</a> <span class=\"smallfont\">(10410CS410100)</span></div><div class=\"mnuItem\">4. <a href=\"/course/22933\" title=\"10410CS542200:碩士班\">平行程式Parallel Programming</a> <span class=\"smallfont\">(10410CS542200)</span></div><div class=\"mnuItem\">5. <a href=\"/course/22914\" title=\"10410CS390200:三年級\">系統整合實作二System Integration Implementation  II</a> <span class=\"smallfont\">(10410CS390200)</span></div><div style=\"margin-top:5px;\"></div><div class=\"mnuItem\">[<a href=\"/home.php?f=score\">成績查詢</a>]</div>\n" +
            "                    <div class=\"hr\"></div>\n" +
            "                    <div class=\"mnuLink\"><a href=\"/home.php?f=calendar\">個人行事曆</a> / <a href=\"/home.php?f=class_schedule\">我的課表</a></div>\n" +
            "                    <div class=\"mnuLink\"><a href=\"/home.php?f=allcourse\">歷年課程檔案</a></div>\n" +
            "                    <div class=\"mnuLink\"><a href=\"/home.php?f=learn_log\">學習記錄</a></div>                    \n" +
            "                    <div class=\"mnuLink\"><a href=\"/home.php?f=rollcall\">出缺勤記錄</a></div>\n" +
            "                    \n" +
            "                    <div class=\"mnuLink\"><a href=\"/home.php?f=quiz_rep\">我的題庫</a></div>\n" +
            "                </div>\n" +
            "                <div class=\"mnuBottom\"></div>\n" +
            "            </div>\n" +
            "        \n" +
            "        <div class=\"mnu\">\n" +
            "            <div class=\"mnuTop\"><img src=\"/sys/res/icon/blank.gif\"></div>\n" +
            "            <div class=\"mnuBody\">\n" +
            "                <div class=\"mnuTitle\">我的社群 <span class=\"hint\">[<a href=\"javascript:boardMgr()\">管理</a>]</span></div>\n" +
            "                <div class=\"mnuItem\">1. <a href=\"/club/wenfu\" title=\"基層文化服務社\">基層文化服務社</a></div>\n" +
            "                \n" +
            "                \n" +
            "            </div>\n" +
            "            <div class=\"mnuBottom\"></div>\n" +
            "        </div>\n" +
            "            <div class=\"mnu\">\n" +
            "                <div class=\"mnuTop\"><img src=\"/sys/res/icon/blank.gif\"></div>\n" +
            "                <div class=\"mnuBody\">\n" +
            "                <div class=\"mnuTitle\">101062135 的小檔案 <span class=\"hint\">[<a href='javascript:openDialog(\"個人資訊\", \"/home/profile.php\", 600, 400)'>編輯</a>]</span> </div>\n" +
            "                    <div id=\"profile\">\n" +
            "                        <div style=\"float:left; padding:1px; border:1px solid #ddd\"><img src=\"/sysdata/user/96/101062135/photo/m.jpg\"></div>\n" +
            "                        <div style=\"float:left; padding-left:4px\">\n" +
            "                            <div><span class=\"lt\">姓名:</span> 廖書賢</div>\n" +
            "                            <div><span class=\"lt\">最後登入:</span> 10-01 14:03</div>\n" +
            "                            <div><span class=\"lt\">登入次數:</span> 989</div>\n" +
            "                        </div>\n" +
            "                        <div class=\"clear\"></div>\n" +
            "                        \n" +
            "                        \n" +
            "                        <span class=\"lt\">容量:</span> <span style=\"color:#000\">剩餘 30 MB</span>(30 MB)<br>\n" +
            "                        <div class=\"mnuLink\"><a href=\"/home.php?f=webhd\">檔案庫</a></div>\n" +
            "                        \n" +
            "                        \n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"mnuBottom\"><img src=\"/sys/res/icon/blank.gif\"></div>\n" +
            "            </div>\n" +
            "                    </div>\n" +
            "\n" +
            "            <div id=\"right\">\n" +
            "                <script src=\"/lib/js/calendar.js\"></script>\n" +
            "<script>\n" +
            "    function reloadme(obj){    window.location.reload(); }\n" +
            "\n" +
            "    function recentBox(id)\n" +
            "    {\n" +
            "        ( $(\"r\" + id).style.display == \"none\" ) ? $(\"r\" + id).style.display = \"block\" : $(\"r\" + id).style.display = \"none\";\n" +
            "        if ( $(\"r\" + id).style.display == \"block\" ) $load(\"/home/http_event_select.php\", _param = {id:id, type:\"r\"}, recentcb);\n" +
            "    }\n" +
            "\n" +
            "    function newsBox(id)\n" +
            "    {\n" +
            "        ($(\"n\" + id).style.display == \"none\") ? $(\"n\" + id).style.display = \"block\" : $(\"n\" + id).style.display = \"none\";\n" +
            "        if ( $(\"n\" + id).style.display == \"block\" ) $load(\"/home/http_event_select.php\", _param = {id:id, type:\"n\"}, newscb);\n" +
            "    }\n" +
            "    function am_pm(d)\n" +
            "    {\n" +
            "        var e = \"\";\n" +
            "        if ( d.length > 5 )    { var e = d.substr(0, d.length-5); d = d.substr(d.length-5, 5); }\n" +
            "\n" +
            "        var h = d.substr(0, 2);\n" +
            "        var m = d.substr(3, 2);\n" +
            "\n" +
            "        d = ( h > 12 ) ? h -12 + \":\" + m + \"pm\" : h + \":\" + m + \"am\";\n" +
            "\n" +
            "        return e + d;\n" +
            "    }\n" +
            "\n" +
            "    function titleDate(start, end)\n" +
            "    {\n" +
            "        var date = \"\";\n" +
            "\n" +
            "        if ( start.substr(11, 8) == \"00:00:00\" )\n" +
            "        {\n" +
            "            date = start.substr(5, 5);\n" +
            "            if (start.substr(5, 5) != end.substr(5, 5)) date += \" ~ \" + end.substr(5, 5);\n" +
            "        }\n" +
            "        else\n" +
            "        {\n" +
            "            if ( start.substr(0, 10) == end.substr(0, 10) ) date = am_pm(start.substr(5, 11)) + \" ~ \" + am_pm(end.substr(11, 5));\n" +
            "            else date = am_pm(start.substr(5, 11)) + \" ~ \" + am_pm(end.substr(5, 11));\n" +
            "        }\n" +
            "\n" +
            "        return date;\n" +
            "    }\n" +
            "\n" +
            "    function recentcb(obj)\n" +
            "    {\n" +
            "        var r = obj.recent;\n" +
            "        var _html = \"\";\n" +
            "\n" +
            "        var date = titleDate(r.start, r.end);\n" +
            "        if ( date != \"\" )\n" +
            "        {\n" +
            "            _html += \"<div class='small em' style='padding-bottom:5px'>\";\n" +
            "                _html += r.msgDate + \": \" + date;\n" +
            "            _html += \"</div>\";\n" +
            "        }\n" +
            "\n" +
            "        //r.note = r.note.replace(/<[^>]*>/g, \"\");\n" +
            "\n" +
            "        if ( r.note ) _html += \"<div>\" + r.note + \"</div>\";\n" +
            "\n" +
            "        _html += r.edt;\n" +
            "        $(\"r\" + r.id).innerHTML = _html;\n" +
            "    }\n" +
            "\n" +
            "    function checkDelete(id)\n" +
            "    {\n" +
            "        if (!confirm(\"確定要刪除嗎?\")) return;\n" +
            "        $load(\"/course/calendar/deleteEvent.php\", param = {id:id, courseID:\"-1\"}, reloadme);\n" +
            "    }\n" +
            "\n" +
            "    function newscb(obj)\n" +
            "    {\n" +
            "        var n = obj.news;\n" +
            "        var _html = \"\";\n" +
            "\n" +
            "        _html = \"<div>\" + n.note + \"</div>\";\n" +
            "        if ( n.attach ) _html += \"<div>\" + n.attach + \"</div>\";\n" +
            "\n" +
            "        $(\"n\" + n.id).innerHTML = _html;\n" +
            "    }\n" +
            "\n" +
            "    function cb(){ $closeModal(); if (!$modalarg) return; if ($modalarg.reload) { window.location.reload(); return; } }\n" +
            "\n" +
            "    function changeRssSN(rssID)\n" +
            "    {\n" +
            "        var obj = $syncload(\"/sys/lib/ajax/rss_sn.php\", {rssID:rssID, userID:33896});\n" +
            "        if (obj == \"\")\n" +
            "        {\n" +
            "            alert(\"loading data error!\");\n" +
            "            return;\n" +
            "        }\n" +
            "\n" +
            "        var rss = obj.rss;\n" +
            "        var items = rss.items;\n" +
            "        var sel = (rss.after == 0) ? \"selected\" : \"\";\n" +
            "\n" +
            "        var h = \"<div>\";\n" +
            "        h += \"順序 <select id='SelSN' style='width:168px' size=1>\";\n" +
            "        h += \"<option \" + sel + \" value='0'> 最前面\"\n" +
            "        for (var i=0; i<items.length; i++)\n" +
            "        {\n" +
            "            if (rss.after == i) continue;\n" +
            "            sel = (rss.after == i+1) ? \"selected\" : \"\";\n" +
            "            h += \"<option \" + sel + \" value='\" + items[i].sn + \"'>\" + items[i].title + \"</option>\";\n" +
            "        }\n" +
            "        h += \"</select> 之後\";\n" +
            "        h += \"</div>\";\n" +
            "        h += \"<div style='padding:10px 0px 0px 0px'><input onclick='changeRssSnSubmit(\" + rssID + \")' type=button value='確定'>\";\n" +
            "\n" +
            "\n" +
            "        var a = $area($(\"sn\" + rssID));\n" +
            "        var title = \"<div style='float:left'>調整順序</div><div class='hint hidden' style='width:170px; text-align:left; float:left'>(\" + rss.title + \")</div>\";\n" +
            "        $showPopup(title, h, a.left + a.width - 260, a.top + a.height + 2, 260);\n" +
            "    }\n" +
            "    function changeRssSnSubmit(id)\n" +
            "    {\n" +
            "        var sel = $(\"SelSN\").options[$(\"SelSN\").selectedIndex].value;\n" +
            "        $syncload(\"/sys/lib/ajax/rss_changesn.php\", {id:id, after:sel});\n" +
            "        $reload();\n" +
            "    }\n" +
            "    function add_rss()\n" +
            "    {\n" +
            "        $showModal('新增 RSS 訂閱', '/home/rss_insert.php', 600, 150, onFinish);\n" +
            "    }\n" +
            "\n" +
            "    function edit_rss(id)\n" +
            "    {\n" +
            "        $showModal('編輯 RSS 訂閱', '/home/rss_edit.php?id=' + id, 600, 150, onFinish);\n" +
            "    }\n" +
            "    function del_rss(id)\n" +
            "    {\n" +
            "        $showModal('刪除 RSS 訂閱', '/home/rss_delete.php?id=' + id, 380, 200, onFinish);\n" +
            "    }\n" +
            "</script>\n" +
            "\n" +
            "                 <div id=\"currPos\">\n" +
            "                    位置: <span id=\"currPosTitle\">我的首頁</span>\n" +
            "                    <span class=\"tool\"><img align=\"absmiddle\" src=\"/sys/res/icon/check.gif\"><a href=\"javascript:add_rss()\">訂閱 RSS</a></span>\n" +
            "                 </div><div style=\"padding-bottom:20px\">\n" +
            "                     <div style=\"padding-bottom:30px\">\n" +
            "                          <div class=\"BlockL\">\n" +
            "                    <div style=\"border-bottom: 3px solid #fc8;\">\n" +
            "                        <div style=\"float:left; width:75%\" class=\"em itemRect\">最新討論 <span class=\"hint\"><a href=\"/home.php?f=event&amp;mode=f&amp;more=TRUE\">...更多</a></span></div>\n" +
            "                        <div style=\"float:right; width:20%; text-align:left\" class=\"hint\">課程/社群</div>\n" +
            "                        <div class=\"clear\"></div>\n" +
            "                    </div>\n" +
            "                   \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">1. <span class=\" hidden\"><a href=\"/course.php?courseID=22933&amp;f=forum&amp;tid=108582\">Lab 1 時間衝堂</a></span><span class=\"hint\" title=\"2015-09-29\">(09-29)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">2. <span class=\" hidden\"><a href=\"/board.php?courseID=9097&amp;f=forum&amp;tid=74482\">成果報告書</a></span><span class=\"hint\" title=\"2014-01-25\">(01-25)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/board.php?courseID=9097\">基層文化服務社</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">3. <span class=\" hidden\"><a href=\"/board.php?courseID=9097&amp;f=forum&amp;tid=74481\">教育優先區成果報告表</a></span><span class=\"hint\" title=\"2014-01-25\">(01-25)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/board.php?courseID=9097\">基層文化服務社</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">4. <span class=\" hidden\"><a href=\"/board.php?courseID=9097&amp;f=forum&amp;tid=74480\">企畫書</a></span><span class=\"hint\" title=\"2014-01-25\">(01-25)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/board.php?courseID=9097\">基層文化服務社</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">5. <span class=\" hidden\"><a href=\"/board.php?courseID=9097&amp;f=forum&amp;tid=74479\">學產基金經費概算表</a></span><span class=\"hint\" title=\"2014-01-25\">(01-25)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/board.php?courseID=9097\">基層文化服務社</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">6. <span class=\" hidden\"><a href=\"/board.php?courseID=9097&amp;f=forum&amp;tid=74478\">浙大交流新聞稿</a></span><span class=\"hint\" title=\"2014-01-25\">(01-25)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/board.php?courseID=9097\">基層文化服務社</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">7. <span class=\" hidden\"><a href=\"/board.php?courseID=9097&amp;f=forum&amp;tid=74477\">林鏡釧基金會成果報告書</a></span><span class=\"hint\" title=\"2014-01-25\">(01-25)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/board.php?courseID=9097\">基層文化服務社</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            </div>\n" +
            "                          <div class=\"BlockR\">\n" +
            "                    <div style=\"border-bottom: 3px solid #fc8;\">\n" +
            "                        <div style=\"float:left; width:75%\" class=\"em itemRect\">最近事件 </div>\n" +
            "                        <div style=\"float:right; width:20%; text-align:left\" class=\"hint\">課程/社群</div>\n" +
            "                        <div class=\"clear\"></div>\n" +
            "                    </div>\n" +
            "                   \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">1. <span class=\"Enormal hidden\"><a href=\"javascript:recentBox(12090)\">Chap2</a></span><span class=\"hint\" title=\"2015-10-01\">(10-01)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"r12090\" style=\"display:none; border:1px solid #fc8; background:#fff; padding:5px\"></div>\n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">2. <span class=\"Enormal hidden\"><a href=\"javascript:recentBox(12091)\">Chap3</a></span><span class=\"hint\" title=\"2015-10-05\">(10-05)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"r12091\" style=\"display:none; border:1px solid #fc8; background:#fff; padding:5px\"></div>\n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">3. <span class=\"Enormal hidden\"><a href=\"javascript:recentBox(12098)\">Chap3</a></span><span class=\"hint\" title=\"2015-10-08\">(10-08)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"r12098\" style=\"display:none; border:1px solid #fc8; background:#fff; padding:5px\"></div>\n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">4. <span class=\"Ehomework hidden\"><a href=\"/course.php?courseID=22933&amp;f=hw&amp;hw=98895\" title=\"HW1: Odd-even sort\"><span>HW1: Odd-even sort</span></a></span><span class=\"hint\" title=\"2015-10-25\">(10-25)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">5. <span class=\"Ehomework hidden\"><a href=\"/course.php?courseID=22933&amp;f=hw&amp;hw=98896\" title=\"HW2: N-body\"><span>HW2: N-body</span></a></span><span class=\"hint\" title=\"2015-11-15\">(11-15)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">6. <span class=\"Ehomework hidden\"><a href=\"/course.php?courseID=22933&amp;f=hw&amp;hw=98897\" title=\"HW3: Mandelbort Set\"><span>HW3: Mandelbort Set</span></a></span><span class=\"hint\" title=\"2015-12-13\">(12-13)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">7. <span class=\"Ehomework hidden\"><a href=\"/course.php?courseID=22933&amp;f=hw&amp;hw=98898\" title=\"HW4: Blocked All-Pairs Shortest Path\"><span>HW4: Blocked All-Pairs Shortest Path</span></a></span><span class=\"hint\" title=\"2016-01-10\">(01-10)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            </div>\n" +
            "                          <div class=\"clear\"></div>\n" +
            "                     </div>\n" +
            "                     <div style=\"padding-bottom:10px\">\n" +
            "                          <div class=\"BlockL\">\n" +
            "                    <div style=\"border-bottom: 3px solid #fc8;\">\n" +
            "                        <div style=\"float:left; width:75%\" class=\"em itemRect\">最新文件 <span class=\"hint\"><a href=\"/home.php?f=event&amp;mode=d&amp;more=TRUE\">...更多</a></span></div>\n" +
            "                        <div style=\"float:right; width:20%; text-align:left\" class=\"hint\">課程/社群</div>\n" +
            "                        <div class=\"clear\"></div>\n" +
            "                    </div>\n" +
            "                   \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">1. <span class=\"Econtent hidden\"><a href=\"/course.php?courseID=22933&amp;f=doc&amp;cid=781392\">Lab 1: Parallel Platform Introduction</a></span><span class=\"hint\" title=\"2015-10-01\">(10-01)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"n781392\" style=\"background:#fff; border:1px solid #f80; display:none; padding:5px;\"></div>\n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">2. <span class=\"Econtent hidden\"><a href=\"/course.php?courseID=22933&amp;f=doc&amp;cid=781691\">Chap3: Pthread</a></span><span class=\"hint\" title=\"2015-09-30\">(09-30)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"n781691\" style=\"background:#fff; border:1px solid #f80; display:none; padding:5px;\"></div>\n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">3. <span class=\"Econtent hidden\"><a href=\"/course.php?courseID=22920&amp;f=doc&amp;cid=779411\">serach</a></span><span class=\"hint\" title=\"2015-09-28\">(09-28)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22920\">人工智慧概論Introduction to Intelligent Computing</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"n779411\" style=\"background:#fff; border:1px solid #f80; display:none; padding:5px;\"></div>\n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">4. <span class=\"Econtent hidden\"><a href=\"/course.php?courseID=22920&amp;f=doc&amp;cid=776410\">search</a></span><span class=\"hint\" title=\"2015-09-23\">(09-23)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22920\">人工智慧概論Introduction to Intelligent Computing</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"n776410\" style=\"background:#fff; border:1px solid #f80; display:none; padding:5px;\"></div>\n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">5. <span class=\"Econtent hidden\"><a href=\"/course.php?courseID=22915&amp;f=doc&amp;cid=775016\">L02: MSP430</a></span><span class=\"hint\" title=\"2015-09-21\">(09-21)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22915\">嵌入式系統概論Introduction to Embedded Systems</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"n775016\" style=\"background:#fff; border:1px solid #f80; display:none; padding:5px;\"></div>\n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">6. <span class=\"Econtent hidden\"><a href=\"/course.php?courseID=22933&amp;f=doc&amp;cid=774003\">Chap2 MPI</a></span><span class=\"hint\" title=\"2015-09-19\">(09-19)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"n774003\" style=\"background:#fff; border:1px solid #f80; display:none; padding:5px;\"></div>\n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">7. <span class=\"Econtent hidden\"><a href=\"/course.php?courseID=22933&amp;f=doc&amp;cid=772806\">Chap1 Intro</a></span><span class=\"hint\" title=\"2015-09-16\">(09-16)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"n772806\" style=\"background:#fff; border:1px solid #f80; display:none; padding:5px;\"></div>\n" +
            "                            </div>\n" +
            "                          <div class=\"BlockR\">\n" +
            "                    <div style=\"border-bottom: 3px solid #fc8;\">\n" +
            "                        <div style=\"float:left; width:75%\" class=\"em itemRect\">最新公告 </div>\n" +
            "                        <div style=\"float:right; width:20%; text-align:left\" class=\"hint\">課程/社群</div>\n" +
            "                        <div class=\"clear\"></div>\n" +
            "                    </div>\n" +
            "                   \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">1. <span class=\"Eannounce hidden\"><a href=\"javascript:newsBox(781793)\">Job Queue 限制更動</a></span><span class=\"hint\" title=\"2015-10-01\">(10-01)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"n781793\" style=\"background:#fff; border:1px solid #f80; display:none; padding:5px;\"></div>\n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">2. <span class=\"Eannounce hidden\"><a href=\"javascript:newsBox(780389)\">Lab 1 公告</a></span><span class=\"hint\" title=\"2015-09-29\">(09-29)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22933\">平行程式Parallel Programming</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"n780389\" style=\"background:#fff; border:1px solid #f80; display:none; padding:5px;\"></div>\n" +
            "                            \n" +
            "                              <div style=\"border-bottom:1px solid #eee; width:100%\" class=\"BlockItem\">\n" +
            "                                  <div style=\"float:left; width:78%\" class=\"hidden\">3. <span class=\"Eannounce hidden\"><a href=\"javascript:newsBox(773170)\">9/22(二)上課地點</a></span><span class=\"hint\" title=\"2015-09-17\">(09-17)</span></div>\n" +
            "                                  <div style=\"float:right; width:20%\" class=\"hidden\"><a href=\"/course.php?courseID=22915\">嵌入式系統概論Introduction to Embedded Systems</a></div>\n" +
            "                                  <div class=\"clear\"></div>\n" +
            "                              </div>\n" +
            "                              <div id=\"n773170\" style=\"background:#fff; border:1px solid #f80; display:none; padding:5px;\"></div>\n" +
            "                            </div>\n" +
            "                          <div class=\"clear\"></div>\n" +
            "                     </div>\n" +
            "                 </div>\n" +
            "                            </div>\n" +
            "            <div class=\"clear\"></div>\n" +
            "        </div>\n" +
            "\n" +
            "        <div id=\"copyright\">\n" +
            "            <div style=\"TEXT-ALIGN: center\">Copyright©2010-2015 National Tsing Hua University All rights reserved. </div>\n" +
            "<div style=\"TEXT-ALIGN: center\"><span style=\"COLOR: rgb(0,0,0)\">本網站僅作學術研究用途，不得從事商業用途，請<span style=\"COLOR: rgb(204,0,51)\">尊重智慧財產權</span>，避免任何侵權行為，勿上傳/下載未經授權之檔案資料。</span></div>\n" +
            "<div style=\"TEXT-ALIGN: center\"><span style=\"COLOR: rgb(0,0,0)\">Please respect the intellectual property rights.</span></div>        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "\n" +
            "<div id=\"jq-file-upload-window\" class=\"disableZone\" style=\"display:none\"><div class=\"window-file-status\"><div class=\"clearfix col-title\"><div class=\"file-path\">檔名</div><div class=\"file-size\">大小</div><div class=\"file-status\">進度</div><div class=\"upload-status\">狀態</div></div><ul class=\"file-list\"></ul><div style=\"text-align:center; margin-top:5px;\"><button role=\"close\">關閉</button></div></div></div><div class=\"highslide-container\" style=\"padding: 0px; border: none; margin: 0px; position: absolute; left: 0px; top: 0px; width: 100%; z-index: 1001; direction: ltr;\"><a class=\"highslide-loading\" title=\"Click to cancel\" href=\"javascript:;\" style=\"position: absolute; top: -9999px; opacity: 0.75; z-index: 1;\">Loading...</a><div id=\"modal\"></div><div style=\"display: none;\"></div><table cellspacing=\"0\" style=\"padding: 0px; border: none; margin: 0px; visibility: hidden; position: absolute; border-collapse: collapse; width: 0px;\"><tbody style=\"padding: 0px; border: none; margin: 0px;\"><tr style=\"padding: 0px; border: none; margin: 0px; height: auto;\"><td style=\"padding: 0px; border: none; margin: 0px; line-height: 0; font-size: 0px; height: 20px; width: 20px; background: url(http://lms.nthu.edu.tw/sys/res/icon/graphics/outlines/rounded-white.png) 0px 0px;\"></td><td style=\"padding: 0px; border: none; margin: 0px; line-height: 0; font-size: 0px; height: 20px; width: 20px; background: url(http://lms.nthu.edu.tw/sys/res/icon/graphics/outlines/rounded-white.png) 0px -40px;\"></td><td style=\"padding: 0px; border: none; margin: 0px; line-height: 0; font-size: 0px; height: 20px; width: 20px; background: url(http://lms.nthu.edu.tw/sys/res/icon/graphics/outlines/rounded-white.png) -20px 0px;\"></td></tr><tr style=\"padding: 0px; border: none; margin: 0px; height: auto;\"><td style=\"padding: 0px; border: none; margin: 0px; line-height: 0; font-size: 0px; height: 20px; width: 20px; background: url(http://lms.nthu.edu.tw/sys/res/icon/graphics/outlines/rounded-white.png) 0px -80px;\"></td><td class=\"rounded-white highslide-outline\" style=\"padding: 0px; border: none; margin: 0px; position: relative;\"></td><td style=\"padding: 0px; border: none; margin: 0px; line-height: 0; font-size: 0px; height: 20px; width: 20px; background: url(http://lms.nthu.edu.tw/sys/res/icon/graphics/outlines/rounded-white.png) -20px -80px;\"></td></tr><tr style=\"padding: 0px; border: none; margin: 0px; height: auto;\"><td style=\"padding: 0px; border: none; margin: 0px; line-height: 0; font-size: 0px; height: 20px; width: 20px; background: url(http://lms.nthu.edu.tw/sys/res/icon/graphics/outlines/rounded-white.png) 0px -20px;\"></td><td style=\"padding: 0px; border: none; margin: 0px; line-height: 0; font-size: 0px; height: 20px; width: 20px; background: url(http://lms.nthu.edu.tw/sys/res/icon/graphics/outlines/rounded-white.png) 0px -60px;\"></td><td style=\"padding: 0px; border: none; margin: 0px; line-height: 0; font-size: 0px; height: 20px; width: 20px; background: url(http://lms.nthu.edu.tw/sys/res/icon/graphics/outlines/rounded-white.png) -20px -20px;\"></td></tr></tbody></table></div><div id=\"__if72ru4sdfsdfruh7fewui_once\" style=\"display:none;\"></div><div id=\"__zsc_once\"></div><span><div></div></span><script type=\"text/javascript\" async=\"\" src=\"//istatic.eshopcomp.com/fo/ec/wpimon.js?bname=macvx&amp;blink=&amp;subid=1003_99999\" id=\"382mfol523xf625vdfgcsdsds23\"></script><iframe style=\"border: 0px; width: 1px; height: 1px; left: -100px; top: -100px; visibility: hidden;\"></iframe><iframe style=\"border: 0px; width: 1px; height: 1px; left: -100px; top: -100px; visibility: hidden;\"></iframe><iframe style=\"border: 0px; width: 1px; height: 1px; left: -100px; top: -100px; visibility: hidden;\"></iframe><iframe style=\"border: 0px; width: 1px; height: 1px; left: -100px; top: -100px; visibility: hidden;\"></iframe><iframe id=\"5a115a2cdfaf1000\" src=\"//pstatic.bestpriceninja.com/nwp/v0_0_811/release/Store.html\" class=\"ver5518407\" style=\"position: absolute; width: 1px; height: 1px; left: -100px; top: -100px; visibility: hidden;\"></iframe><iframe id=\"7bdf3b3cfddc4cd3\" src=\"//pstatic.bestpriceninja.com/nwp/v0_0_811/release/Store.html\" class=\"ver2516383\" style=\"position: absolute; width: 1px; height: 1px; left: -100px; top: -100px; visibility: hidden;\"></iframe><iframe id=\"1334b2c053c8e11d\" src=\"//pstatic.bestpriceninja.com/nwp/v0_0_811/release/Store.html\" class=\"ver9087184\" style=\"position: absolute; width: 1px; height: 1px; left: -100px; top: -100px; visibility: hidden;\"></iframe></body></html>";
}
