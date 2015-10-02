package com.example.gordon.ilms.http;

/**
 * Created by gordon on 10/1/15.
 */
public class Test {
    public static String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
            "<title>國立清華大學 iLMS數位學習平台</title>\n" +
            "<style>@import URL(\"/sys/style/dom.css\");</style>\n" +
            "<style>@import URL(\"/sys/style/style.css\");</style>\n" +
            "<style>@import URL(\"/sys/style/clist.css\");</style>\n" +
            "<style>@import URL(\"/sys/style/cbutton.css\");</style>\n" +
            "<style>@import URL(\"/sys/style/home.css\");</style>\n" +
            "<style>@import URL(\"/sys/style/form.css\");</style>\n" +
            "<style>@import URL('/sys/style/highslide.css');</style>\n" +
            "\n" +
            "<script type=\"text/javascript\" src=\"/sys/lib/js/res.php\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/sys/lib/js/lib.js\"></script>\n" +
            "<script type=\"text/javascript\" src='/sys/lib/js/jquery.js'></script>\n" +
            "<script type=\"text/javascript\" src='/sys/lib/js/jquery.ui.widget.js'></script>\n" +
            "<script type=\"text/javascript\" src='/sys/lib/js/jquery.iframe-transport.js'></script>\n" +
            "<script type=\"text/javascript\" src='/sys/lib/js/jquery.fileupload.js'></script>\n" +
            "<script type=\"text/javascript\" src='/sys/lib/js/fileUpload.js'></script>\n" +
            "\n" +
            "<script type=\"text/javascript\" src='/sys/lib/js/dom.js'></script>\n" +
            "<script src='/sys/lib/js/highslide-with-html.js'></script>\n" +
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
            "</head>\n" +
            "<body align='center'>\n" +
            "<div id=wrapper>\n" +
            "    <div id=base>\n" +
            "        <div>\n" +
            "            <div id=sysbar>\n" +
            "    <div id=logo><a href='/home.php'><img src='/banner.gif' border=0></a></div>\n" +
            "    <div id=sign>\n" +
            "        <div id=login>\n" +
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
            "    var checkSecCode;       \n" +
            "        checkSecCode = false;\n" +
            "    function openDialog(title, url, w, h) { $showModal(title, url, w, h, onOpenDialogLoad); }\n" +
            "    function onOpenDialogLoad() { $reload(); }\n" +
            "</script>\n" +
            "<script type=\"text/javascript\" src='/lib/js/login.js'></script>\n" +
            "<img src='/sys/res/icon/arrow_right.gif' style='padding:0px 2px 0px 10px; vertical-align: middle'><a href='/course/index.php' id='_lms'>LMS</a><img src='/sys/res/icon/line.gif' style='padding: 0 5px;'><a href='/board/index.php' id='_cm'>知識社群</a><span style='font-weight:bold'><img src='/sys/res/icon/arrow_right.gif' style='padding:0px 2px 0px 10px; vertical-align: middle'><a href='/home.php'>我的首頁</a><img src='/sys/res/icon/line.gif' style='padding: 0 5px;'></span><a href='javascript:logout()'>登出(101062124)</a>        </div>\n" +
            "\n" +
            "        <div style='text-align:right;'>\n" +
            "中文(台灣)<img src='/sys/res/icon/line.gif' style='padding: 0 8px;'><a href='javascript:m_changeLang(\"en-us\")'>English(US)</a><img src='/sys/res/icon/line.gif' style='padding: 0 8px;'><a href='http://lms.nthu.edu.tw/course/74' target=_blank>Q&amp;A</a><img src='/sys/res/icon/line.gif' style='padding: 0 8px;'>線上人數: <span id='counter'>530</span>&nbsp;&nbsp;<input id=txtSearch onkeypress='$searchEnter(event)' type='text' style='height:15px; font-size:12px; width:80px;'>&nbsp;<img style='cursor:pointer' onclick='$searchSubmit()' src='/sys/res/icon/find.png' align=absmiddle>        </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=clear></div>\n" +
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
            "        <div id=mainbody>\n" +
            "            <div id=left>\n" +
            "                \n" +
            "            <div class=mnu>\n" +
            "                <div class=mnuTop><img src='/sys/res/icon/blank.gif'></div>\n" +
            "                <div class=mnuBody>\n" +
            "                    <div class=mnuTitle>我的課程</div>\n" +
            "                    <div class=hint style='margin-top:5px;'>[學期: 1041]</div><div class=mnuItem>1. <a href='/course/22942' title='10410CS631200:碩士班'>平行計算方法設計Parallel Algorithm Design</a> <span class=smallfont>(10410CS631200)</span></div><div class=mnuItem>2. <a href='/course/23707' title='10410LS114300:一年級'>當代認知神經科學：腦與心智Contemporary Cognitive Neuroscience: Brain and Mind</a> <span class=smallfont>(10410LS114300)</span></div><div class=mnuItem>3. <a href='/course/23408' title='10410GEC140202:一年級'>社會文化分析Social and cultural Analysis</a> <span class=smallfont>(10410GEC140202)</span></div><div class=mnuItem>4. <a href='/course/23569' title='10410ISA558100:碩士班'>(FUN) 程式創作Fun Programming</a> <span class=smallfont>(10410ISA558100)</span></div><div class=mnuItem>5. <a href='/course/22922' title='10410CS510000:碩士班'>高等計算機結構Advanced Computer Architecture</a> <span class=smallfont>(10410CS510000)</span></div><div style='margin-top:5px;'></div><div class=mnuItem>[<a href='/home.php?f=score'>成績查詢</a>]</div>\n" +
            "                    <div class=hr></div>\n" +
            "                    <div class=mnuLink><a href='/home.php?f=calendar'>個人行事曆</a> / <a href='/home.php?f=class_schedule'>我的課表</a></div>\n" +
            "                    <div class=mnuLink><a href='/home.php?f=allcourse'>歷年課程檔案</a></div>\n" +
            "                    <div class=mnuLink><a href='/home.php?f=learn_log'>學習記錄</a></div>                    \n" +
            "                    <div class=mnuLink><a href='/home.php?f=rollcall'>出缺勤記錄</a></div>\n" +
            "                    \n" +
            "                    <div class=mnuLink><a href='/home.php?f=quiz_rep'>我的題庫</a></div>\n" +
            "                </div>\n" +
            "                <div class=mnuBottom></div>\n" +
            "            </div>\n" +
            "        \n" +
            "        <div class=mnu>\n" +
            "            <div class=mnuTop><img src='/sys/res/icon/blank.gif'></div>\n" +
            "            <div class=mnuBody>\n" +
            "                <div class=mnuTitle>我的社群 <span class=hint></span></div>\n" +
            "                目前尚無資料\n" +
            "                \n" +
            "                \n" +
            "            </div>\n" +
            "            <div class=mnuBottom></div>\n" +
            "        </div>\n" +
            "            <div class=mnu>\n" +
            "                <div class=mnuTop><img src='/sys/res/icon/blank.gif'></div>\n" +
            "                <div class=mnuBody>\n" +
            "                <div class=mnuTitle>101062124 的小檔案 <span class=hint>[<a href='javascript:openDialog(\"個人資訊\", \"/home/profile.php\", 600, 400)'>編輯</a>]</span> </div>\n" +
            "                    <div id=profile>\n" +
            "                        <div style='float:left; padding:1px; border:1px solid #ddd'><img src='/sysdata/user/88/101062124/photo/m.jpg'></div>\n" +
            "                        <div style='float:left; padding-left:4px'>\n" +
            "                            <div><span class=lt>姓名:</span> 戴宏穎</div>\n" +
            "                            <div><span class=lt>最後登入:</span> 10-01 15:54</div>\n" +
            "                            <div><span class=lt>登入次數:</span> 955</div>\n" +
            "                        </div>\n" +
            "                        <div class=clear></div>\n" +
            "                        \n" +
            "                        \n" +
            "                        <span class=lt>容量:</span> <span style='color:#000'>剩餘 30 MB</span>(30 MB)</br>\n" +
            "                        <div class=mnuLink><a href='/home.php?f=webhd'>檔案庫</a></div>\n" +
            "                        \n" +
            "                        \n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=mnuBottom><img src='/sys/res/icon/blank.gif'></div>\n" +
            "            </div>\n" +
            "                    </div>\n" +
            "\n" +
            "            <div id=right>\n" +
            "                <script src='/lib/js/calendar.js'></script>\n" +
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
            "        var obj = $syncload(\"/sys/lib/ajax/rss_sn.php\", {rssID:rssID, userID:33888});\n" +
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
            "                 <div id=currPos>\n" +
            "                    位置: <span id=currPosTitle>我的首頁</span>\n" +
            "                    <span class=tool><img align=absmiddle src='/sys/res/icon/check.gif'><a href='javascript:add_rss()'>訂閱 RSS</a></span>\n" +
            "                 </div><div style='padding-bottom:20px'>\n" +
            "                     <div style='padding-bottom:30px'>\n" +
            "                          <div class=BlockL>\n" +
            "                    <div style='border-bottom: 3px solid #fc8;'>\n" +
            "                        <div style='float:left; width:75%' class='em itemRect'>最新討論 </div>\n" +
            "                        <div style='float:right; width:20%; text-align:left' class=hint>課程/社群</div>\n" +
            "                        <div class=clear></div>\n" +
            "                    </div>\n" +
            "                   \n" +
            "                              <div style='border-bottom:1px solid #eee; width:100%' class='BlockItem'>\n" +
            "                                  <div style='float:left; width:78%' class=hidden>1. <span class=' hidden'><a href='/course.php?courseID=23569&f=forum&tid=108733'>office hour</a></span><span class=hint title='2015-10-01'>(10-01)</span></div>\n" +
            "                                  <div style='float:right; width:20%' class=hidden><a href='/course.php?courseID=23569'>程式創作Fun Programming</a></div>\n" +
            "                                  <div class=clear></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            </div>\n" +
            "                          <div class=BlockR>\n" +
            "                    <div style='border-bottom: 3px solid #fc8;'>\n" +
            "                        <div style='float:left; width:75%' class='em itemRect'>最近事件 </div>\n" +
            "                        <div style='float:right; width:20%; text-align:left' class=hint>課程/社群</div>\n" +
            "                        <div class=clear></div>\n" +
            "                    </div>\n" +
            "                   \n" +
            "                              <div style='border-bottom:1px solid #eee; width:100%' class='BlockItem'>\n" +
            "                                  <div style='float:left; width:78%' class=hidden>1. <span class='Ehomework hidden'><a href='/course.php?courseID=23569&f=hw&hw=99702' title='Week03 in class exercise'><span >Week03 in class exercise</span></a></span><span class=hint title='2015-10-07'>(10-07)</span></div>\n" +
            "                                  <div style='float:right; width:20%' class=hidden><a href='/course.php?courseID=23569'>程式創作Fun Programming</a></div>\n" +
            "                                  <div class=clear></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            \n" +
            "                              <div style='border-bottom:1px solid #eee; width:100%' class='BlockItem'>\n" +
            "                                  <div style='float:left; width:78%' class=hidden>2. <span class='Ehomework hidden'><a href='/course.php?courseID=23569&f=hw&hw=99729' title='Week03 homework'><span >Week03 homework</span></a></span><span class=hint title='2015-10-07'>(10-07)</span></div>\n" +
            "                                  <div style='float:right; width:20%' class=hidden><a href='/course.php?courseID=23569'>程式創作Fun Programming</a></div>\n" +
            "                                  <div class=clear></div>\n" +
            "                              </div>\n" +
            "                              \n" +
            "                            </div>\n" +
            "                          <div class=clear></div>\n" +
            "                     </div>\n" +
            "                     <div style='padding-bottom:10px'>\n" +
            "                          <div class=BlockL>\n" +
            "                    <div style='border-bottom: 3px solid #fc8;'>\n" +
            "                        <div style='float:left; width:75%' class='em itemRect'>最新文件 <span class=hint><a href='/home.php?f=event&mode=d&more=TRUE'>...更多</a></span></div>\n" +
            "                        <div style='float:right; width:20%; text-align:left' class=hint>課程/社群</div>\n" +
            "                        <div class=clear></div>\n" +
            "                    </div>\n" +
            "                   \n" +
            "                              <div style='border-bottom:1px solid #eee; width:100%' class='BlockItem'>\n" +
            "                                  <div style='float:left; width:78%' class=hidden>1. <span class='Econtent hidden'><a href='/course.php?courseID=22942&f=doc&cid=781983'>CH 2 - Part I</a></span><span class=hint title='2015-10-01'>(10-01)</span></div>\n" +
            "                                  <div style='float:right; width:20%' class=hidden><a href='/course.php?courseID=22942'>平行計算方法設計Parallel Algorithm Design</a></div>\n" +
            "                                  <div class=clear></div>\n" +
            "                              </div>\n" +
            "                              <div id='n781983' style='background:#fff; border:1px solid #f80; display:none; padding:5px;'></div>\n" +
            "                            \n" +
            "                              <div style='border-bottom:1px solid #eee; width:100%' class='BlockItem'>\n" +
            "                                  <div style='float:left; width:78%' class=hidden>2. <span class='Econtent hidden'><a href='/course.php?courseID=23569&f=doc&cid=781397'>Week 03</a></span><span class=hint title='2015-09-30'>(09-30)</span></div>\n" +
            "                                  <div style='float:right; width:20%' class=hidden><a href='/course.php?courseID=23569'>程式創作Fun Programming</a></div>\n" +
            "                                  <div class=clear></div>\n" +
            "                              </div>\n" +
            "                              <div id='n781397' style='background:#fff; border:1px solid #f80; display:none; padding:5px;'></div>\n" +
            "                            \n" +
            "                              <div style='border-bottom:1px solid #eee; width:100%' class='BlockItem'>\n" +
            "                                  <div style='float:left; width:78%' class=hidden>3. <span class='Econtent hidden'><a href='/course.php?courseID=22922&f=doc&cid=780601'>L03-Perf</a></span><span class=hint title='2015-09-29'>(09-29)</span></div>\n" +
            "                                  <div style='float:right; width:20%' class=hidden><a href='/course.php?courseID=22922'>高等計算機結構Advanced Computer Architecture</a></div>\n" +
            "                                  <div class=clear></div>\n" +
            "                              </div>\n" +
            "                              <div id='n780601' style='background:#fff; border:1px solid #f80; display:none; padding:5px;'></div>\n" +
            "                            \n" +
            "                              <div style='border-bottom:1px solid #eee; width:100%' class='BlockItem'>\n" +
            "                                  <div style='float:left; width:78%' class=hidden>4. <span class='Econtent hidden'><a href='/course.php?courseID=23569&f=doc&cid=773436'>Week 02</a></span><span class=hint title='2015-09-25'>(09-25)</span></div>\n" +
            "                                  <div style='float:right; width:20%' class=hidden><a href='/course.php?courseID=23569'>程式創作Fun Programming</a></div>\n" +
            "                                  <div class=clear></div>\n" +
            "                              </div>\n" +
            "                              <div id='n773436' style='background:#fff; border:1px solid #f80; display:none; padding:5px;'></div>\n" +
            "                            \n" +
            "                              <div style='border-bottom:1px solid #eee; width:100%' class='BlockItem'>\n" +
            "                                  <div style='float:left; width:78%' class=hidden>5. <span class='Econtent hidden'><a href='/course.php?courseID=22922&f=doc&cid=774407'>L02-Technology Trend</a></span><span class=hint title='2015-09-21'>(09-21)</span></div>\n" +
            "                                  <div style='float:right; width:20%' class=hidden><a href='/course.php?courseID=22922'>高等計算機結構Advanced Computer Architecture</a></div>\n" +
            "                                  <div class=clear></div>\n" +
            "                              </div>\n" +
            "                              <div id='n774407' style='background:#fff; border:1px solid #f80; display:none; padding:5px;'></div>\n" +
            "                            \n" +
            "                              <div style='border-bottom:1px solid #eee; width:100%' class='BlockItem'>\n" +
            "                                  <div style='float:left; width:78%' class=hidden>6. <span class='Econtent hidden'><a href='/course.php?courseID=22942&f=doc&cid=773762'>CH1</a></span><span class=hint title='2015-09-18'>(09-18)</span></div>\n" +
            "                                  <div style='float:right; width:20%' class=hidden><a href='/course.php?courseID=22942'>平行計算方法設計Parallel Algorithm Design</a></div>\n" +
            "                                  <div class=clear></div>\n" +
            "                              </div>\n" +
            "                              <div id='n773762' style='background:#fff; border:1px solid #f80; display:none; padding:5px;'></div>\n" +
            "                            \n" +
            "                              <div style='border-bottom:1px solid #eee; width:100%' class='BlockItem'>\n" +
            "                                  <div style='float:left; width:78%' class=hidden>7. <span class='Econtent hidden'><a href='/course.php?courseID=23569&f=doc&cid=770982'>Week 01</a></span><span class=hint title='2015-09-16'>(09-16)</span></div>\n" +
            "                                  <div style='float:right; width:20%' class=hidden><a href='/course.php?courseID=23569'>程式創作Fun Programming</a></div>\n" +
            "                                  <div class=clear></div>\n" +
            "                              </div>\n" +
            "                              <div id='n770982' style='background:#fff; border:1px solid #f80; display:none; padding:5px;'></div>\n" +
            "                            </div>\n" +
            "                          <div class=BlockR>\n" +
            "                    <div style='border-bottom: 3px solid #fc8;'>\n" +
            "                        <div style='float:left; width:75%' class='em itemRect'>最新公告 </div>\n" +
            "                        <div style='float:right; width:20%; text-align:left' class=hint>課程/社群</div>\n" +
            "                        <div class=clear></div>\n" +
            "                    </div>\n" +
            "                   \n" +
            "                              <div style='border-bottom:1px solid #eee; width:100%' class='BlockItem'>\n" +
            "                                  <div style='float:left; width:78%' class=hidden>1. <span class='Eannounce hidden'><a href='javascript:newsBox(771436)'>本課程從 9/17 (四) 開始上課</a></span><span class=hint title='2015-09-14'>(09-14)</span></div>\n" +
            "                                  <div style='float:right; width:20%' class=hidden><a href='/course.php?courseID=22942'>平行計算方法設計Parallel Algorithm Design</a></div>\n" +
            "                                  <div class=clear></div>\n" +
            "                              </div>\n" +
            "                              <div id='n771436' style='background:#fff; border:1px solid #f80; display:none; padding:5px;'></div>\n" +
            "                            </div>\n" +
            "                          <div class=clear></div>\n" +
            "                     </div>\n" +
            "                 </div>\n" +
            "                            </div>\n" +
            "            <div class=clear></div>\n" +
            "        </div>\n" +
            "\n" +
            "        <div id=\"copyright\">\n" +
            "            <DIV style=\"TEXT-ALIGN: center\">Copyright&copy;2010-2015 National Tsing Hua University All rights reserved. </DIV>\n" +
            "<DIV style=\"TEXT-ALIGN: center\"><SPAN style=\"COLOR: rgb(0,0,0)\">本網站僅作學術研究用途，不得從事商業用途，請<SPAN style=\"COLOR: rgb(204,0,51)\">尊重智慧財產權</SPAN>，避免任何侵權行為，勿上傳/下載未經授權之檔案資料。</SPAN></DIV>\n" +
            "<DIV style=\"TEXT-ALIGN: center\"><SPAN style=\"COLOR: rgb(0,0,0)\">Please respect the intellectual property rights.</SPAN></DIV>        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";
}