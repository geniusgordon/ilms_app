package com.geniusgordon.ilms.app.forum;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geniusgordon.ilms.HtmlFix;
import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.model.Attachment;
import com.geniusgordon.ilms.model.Reply;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gordon on 9/29/15.
 */
public class ReplyViewHolder {

    private Context context;
    private ViewGroup mView;
    @Bind(R.id.header) View header;
    @Bind(R.id.author) TextView authorTxt;
    @Bind(R.id.account) TextView accountTxt;
    @Bind(R.id.email) TextView emailTxt;
    @Bind(R.id.time) TextView timeTxt;
    @Bind(R.id.floor) TextView floorTxt;
    @Bind(R.id.content) TextView contentTxt;

    private boolean folded;
    private Reply reply;

    public ReplyViewHolder(Context context, ViewGroup view, Reply reply) {
        folded = false;
        this.context = context;
        this.reply = reply;

        mView = view;
        ButterKnife.bind(this, view);

        contentTxt.setMovementMethod(LinkMovementMethod.getInstance());

        authorTxt.setText(reply.getName());
        accountTxt.setText(reply.getAccount());
        emailTxt.setText(reply.getEmail());
        floorTxt.setText(reply.getFloor() + "F");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = reply.getTime()==null ? "" : df.format(reply.getTime());
        timeTxt.setText(time);

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (folded) {
//                    setupContent();
                    mView.addView(contentTxt);
                } else {
//                    contentTxt.setText("");
                    mView.removeView(contentTxt);
                }
                folded = !folded;
            }
        });

        setupContent();
    }

    private void setupContent() {
        contentTxt.setText("");
        contentTxt.setText(HtmlFix.correctLinkPaths(Html.fromHtml(reply.getContent())));

        if (reply.getAttachments().size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            String attachmentStr = context.getString(R.string.attachment);
            stringBuilder.append("<br><br><p><strong>" + attachmentStr + "</strong><br><br>");
            for (Attachment attach : reply.getAttachments()) {
                String a = "<a href='%s'>%s</a>&nbsp;%s<br><br>";
                stringBuilder.append(String.format(a, attach.getUrl(), attach.getTitle(), attach.getSize()));
            }
            stringBuilder.append("</p>");
            contentTxt.append(Html.fromHtml(stringBuilder.toString()));
        }
    }

    public View getView() {
        return mView;
    }
}
