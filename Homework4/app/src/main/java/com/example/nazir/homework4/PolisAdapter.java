package com.example.nazir.homework4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nazir on 10.08.2017.
 */

public class PolisAdapter extends RecyclerView.Adapter<PolisAdapter.ViewHolder> {

    List<PolisEntity> items;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_polis, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(PolisEntity entity) {
        if (items == null) {
            items = new ArrayList<>();
        }

        items.add(entity);

        notifyDataSetChanged();
    }

    public void updateItems(List<PolisEntity> items) {
        if (items == null) {
            return;
        }

        this.items = items;

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView   iconPolis;
        TextView    textPolisInfo;
        int         textColor1;
        int         textColor2;
        int         textColor_red;

        ProgressBar progress;
        TextView    textDates;
        ImageView   imageBuy;

        Drawable    greenStyle;
        Drawable    orangeStyle;
        Drawable    redStyle;


        public ViewHolder(final View itemView){
            super(itemView);

            iconPolis       = (ImageView) itemView.findViewById(R.id.image_polis);
            textPolisInfo   = (TextView) itemView.findViewById(R.id.textPolisInfo);

            textColor1      = itemView.getResources().getColor(R.color.colorTextPolisType);
            textColor2      = itemView.getResources().getColor(R.color.colorTextPolisNum);
            textColor_red   = itemView.getResources().getColor(R.color.colorProgressRed);

            progress        = (ProgressBar) itemView.findViewById(R.id.progress);
            textDates       = (TextView) itemView.findViewById(R.id.polisDates);
            imageBuy        = (ImageView) itemView.findViewById(R.id.image_buy);

            greenStyle      = itemView.getResources().getDrawable(R.drawable.progress_green);
            orangeStyle     = itemView.getResources().getDrawable(R.drawable.progress_orange);
            redStyle        = itemView.getResources().getDrawable(R.drawable.progress_red);
        }

        public void bindData(final PolisEntity entity) {

            String st_TextPolis = "";
            String st_Dates = "";
            switch (entity.getPolisType())
            {
                case 0:
                {
                    iconPolis.setImageResource(R.drawable.icon_car);
                    st_TextPolis = "ОСАГО ";

                    SimpleDateFormat simpleDate =  new SimpleDateFormat("dd.MM.yyyy");
                    if (entity.getPolisStart() != null)
                        st_Dates = st_Dates + "\nс " + simpleDate.format(entity.getPolisStart());

                    if (entity.getPolisEnd() != null)
                        st_Dates = st_Dates + " по " + simpleDate.format(entity.getPolisEnd());
                } break;

                case 1:
                {
                    iconPolis.setImageResource(R.drawable.icon_car_kasko);
                    st_TextPolis = "КАСКО ";

                    SimpleDateFormat simpleDate =  new SimpleDateFormat("dd.MM.yyyy");
                    if (entity.getPolisStart() != null)
                        st_Dates += "\nс " + simpleDate.format(entity.getPolisStart());

                    if (entity.getPolisEnd() != null)
                        st_Dates += " по " + simpleDate.format(entity.getPolisEnd());
                } break;

                case 2:
                {
                    iconPolis.setImageResource(R.drawable.icon_key);
                    st_TextPolis = "Имущество ";
                } break;

                case 3:
                {
                    iconPolis.setImageResource(R.drawable.icon_medec);
                    st_TextPolis = "Медицинское страхование ";
                } break;
            }

            SpannableStringBuilder builder = new SpannableStringBuilder();

            SpannableString str1= new SpannableString(st_TextPolis);
            str1.setSpan(new ForegroundColorSpan(textColor1), 0, str1.length(), 0);
            builder.append(str1);

            SpannableString str2= new SpannableString(entity.getPolisNumber() + "\n");
            str2.setSpan(new ForegroundColorSpan(textColor2), 0, str2.length(), 0);
            builder.append(str2);

            SpannableString str3= new SpannableString(entity.getPolisObject());
            str3.setSpan(new ForegroundColorSpan(textColor1), 0, str3.length(), 0);
            builder.append(str3);

            SpannableString str4= new SpannableString(st_Dates);
            str4.setSpan(new ForegroundColorSpan(textColor2), 0, str4.length(), 0);
            builder.append(str4);

            textPolisInfo.setText(builder, TextView.BufferType.SPANNABLE);

            //нижнее поле отображения
            Date curDate = new Date();
            if (curDate.after(entity.getPolisEnd())) {
                textDates.setText("Срок действия полиса истек");
                textDates.setTextColor(textColor_red);
                imageBuy.setVisibility(View.VISIBLE);

                progress.setMax(100);
                progress.setProgress(100);
                progress.setProgressDrawable(redStyle);
            }
            else {
                //расчет оставшихся дней
                long diff_ost = entity.getPolisEnd().getTime() - (new Date().getTime());
                int ostDays = (int) (diff_ost / (1000 * 60 * 60 * 24));

                long diff_all = entity.getPolisEnd().getTime() - entity.getPolisStart().getTime();
                int ostAll = (int) (diff_all / (1000 * 60 * 60 * 24));

                int textDaysColor;

                //окончание слово ДЕНЬ
                String textDaysText = "";
                int ost__days_ost = ostDays % 10;
                if (ost__days_ost == 1) {
                    textDaysText = "день";
                }
                else if ((ost__days_ost > 4) || (ost__days_ost == 0) || ((ostDays > 10) && (ostDays < 20))) {
                    textDaysText = "дней";
                }
                else {
                    textDaysText = "дня";
                }

                //отображение оставшихся дней
                SpannableStringBuilder builder2 = new SpannableStringBuilder();

                SpannableString str1_2 = new SpannableString("Осталось ");
                str1_2.setSpan(new ForegroundColorSpan(textColor2), 0, str1_2.length(), 0);
                builder2.append(str1_2);

                if (ostDays <= 7) {
                    textDaysColor = textColor_red;
                }
                else {
                    textDaysColor = textColor2;
                }

                SpannableString str2_2 = new SpannableString(Integer.toString(ostDays) + " " + textDaysText);
                str2_2.setSpan(new ForegroundColorSpan(textDaysColor), 0, str2_2.length(), 0);
                builder2.append(str2_2);

                String data_str = " (до ";
                SimpleDateFormat simpleDate = new SimpleDateFormat("dd.MM.yyyy");
                if (entity.getPolisEnd() != null)
                    data_str += simpleDate.format(entity.getPolisEnd());
                data_str += ")";
                SpannableString str3_2 = new SpannableString(data_str);
                str3_2.setSpan(new ForegroundColorSpan(textColor2), 0, str3_2.length(), 0);
                builder2.append(str3_2);

                textDates.setText(builder2, TextView.BufferType.SPANNABLE);

                //Прогресс
                progress.setMax(ostAll);
                progress.setProgress(ostAll - ostDays);

                if (ostDays <= 7) {
                    progress.setProgressDrawable(redStyle);
                } else if (ostDays <= 30) {
                    progress.setProgressDrawable(orangeStyle);
                }
                else {
                    progress.setProgressDrawable(greenStyle);
                }

                // покупка
                if (ostDays <= 30) {
                    imageBuy.setVisibility(View.VISIBLE);
                }
                else {
                    imageBuy.setVisibility(View.INVISIBLE);
                }
            }

            imageBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Date newDate = new Date();
                    Date endDate = new Date();
                    endDate.setYear(endDate.getYear() + 1);

                    entity.setPolisStart(newDate);
                    entity.setPolisEnd(endDate);
                }
            });
        }

    }
}
