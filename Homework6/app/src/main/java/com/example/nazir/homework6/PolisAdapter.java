package com.example.nazir.homework6;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;



/**
 * Created by nazir on 10.08.2017.
 */

public class PolisAdapter extends RecyclerView.Adapter<PolisAdapter.ViewHolder> {

    static class PolisDateComparatorUp implements Comparator<PolisEntity>
    {
        public int compare(PolisEntity p1, PolisEntity p2) {
            Date end1 = p1.getPolisEnd();
            Date end2 = p2.getPolisEnd();

            int res = end1.compareTo(end2);
            if (res > 0)      return 1;
            else
            if (res < 0)    return -1;

            return 0;
        }
    }

    static class PolisDateComparatorDown implements Comparator<PolisEntity>
    {
        public int compare(PolisEntity p1, PolisEntity p2) {
            Date end1 = p1.getPolisEnd();
            Date end2 = p2.getPolisEnd();

            int res = end1.compareTo(end2);
            if (res > 0)      return -1;
            else
            if (res < 0)    return 1;

            return 0;
        }
    }


    List<PolisEntity> items;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_polis, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(this, items.get(position));
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

    public void setNewItems(List<PolisEntity> newItems) {
        if (newItems == null) {
           return;
        }

        items = newItems;
        notifyDataSetChanged();
    }

    public void SortItemsUp() {

        PolisDateComparatorUp pc = new PolisDateComparatorUp();
        Collections.sort(items, pc);
        notifyDataSetChanged();
    }

    public void SortItemsDown() {
        PolisDateComparatorDown pc = new PolisDateComparatorDown();
        Collections.sort(items, pc);
        notifyDataSetChanged();
    }

    private void setChangedData() {
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView   iconPolis;
        TextView    textPolisInfo;

        int         textColor_black;
        int         textColor_grey;
        int         textColor_red;

        String      strPolisOSAGO;
        String      strPolisKASKO;
        String      strPolisFlat;
        String      strPolisAid;

        ProgressBar progress;
        TextView    textDates;
        ImageView   imageBuy;

        Drawable    greenStyle;
        Drawable    orangeStyle;
        Drawable    redStyle;

        PolisAdapter parentAdapter;


        public ViewHolder(final View itemView){
            super(itemView);

            iconPolis       = (ImageView) itemView.findViewById(R.id.image_polis);
            textPolisInfo   = (TextView) itemView.findViewById(R.id.textPolisInfo);

            textColor_black = itemView.getResources().getColor(R.color.colorTextPolisType);
            textColor_grey  = itemView.getResources().getColor(R.color.colorTextPolisNum);
            textColor_red   = itemView.getResources().getColor(R.color.colorProgressRed);

            progress        = (ProgressBar) itemView.findViewById(R.id.progress);
            textDates       = (TextView) itemView.findViewById(R.id.polisDates);
            imageBuy        = (ImageView) itemView.findViewById(R.id.image_buy);

            greenStyle      = itemView.getResources().getDrawable(R.drawable.progress_green);
            orangeStyle     = itemView.getResources().getDrawable(R.drawable.progress_orange);
            redStyle        = itemView.getResources().getDrawable(R.drawable.progress_red);

            strPolisOSAGO   = itemView.getResources().getString(R.string.polisOSAGO);
            strPolisKASKO   = itemView.getResources().getString(R.string.polisKASKO);
            strPolisFlat    = itemView.getResources().getString(R.string.polisFlat);
            strPolisAid     = itemView.getResources().getString(R.string.polisAid);

            parentAdapter   = null;
        }

        public void bindData(PolisAdapter parent, final PolisEntity entity) {

            parentAdapter = parent;
            configurePolisInfoText(entity);
        }

        private void configurePolisInfoText(final PolisEntity entity) {
            String strTextPolis = "";
            String strDates = "";

            switch (entity.getPolisType())
            {
                case polisOsago:
                {
                    iconPolis.setImageResource(R.drawable.icon_car);
                    strTextPolis = strPolisOSAGO;
                    strDates = getDatePeriodString(entity.getPolisStart(), entity.getPolisEnd());
                } break;

                case polisKasko:
                {
                    iconPolis.setImageResource(R.drawable.icon_car_kasko);
                    strTextPolis = strPolisKASKO;
                    strDates = getDatePeriodString(entity.getPolisStart(), entity.getPolisEnd());
                } break;

                case polisFlat:
                {
                    iconPolis.setImageResource(R.drawable.icon_key);
                    strTextPolis = strPolisFlat;
                } break;

                case polisAid:
                {
                    iconPolis.setImageResource(R.drawable.icon_medec);
                    strTextPolis = strPolisAid;
                } break;
            }

            SpannableStringBuilder builder = new SpannableStringBuilder();

            SpannableString str1 = new SpannableString(strTextPolis + " ");
            str1.setSpan(new ForegroundColorSpan(textColor_black), 0, str1.length(), 0);
            builder.append(str1);

            SpannableString str2 = new SpannableString(entity.getPolisNumber() + "\n");
            str2.setSpan(new ForegroundColorSpan(textColor_grey), 0, str2.length(), 0);
            builder.append(str2);

            SpannableString str3 = new SpannableString(entity.getPolisObject() + "\n");
            str3.setSpan(new ForegroundColorSpan(textColor_black), 0, str3.length(), 0);
            builder.append(str3);

            SpannableString str4 = new SpannableString(strDates);
            str4.setSpan(new ForegroundColorSpan(textColor_grey), 0, str4.length(), 0);
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
                int ostDays = entity.countDaysBetween(new Date(),entity.getPolisEnd());
                int ostAll =  entity.countDaysBetween(entity.getPolisStart(), entity.getPolisEnd());
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
                str1_2.setSpan(new ForegroundColorSpan(textColor_grey), 0, str1_2.length(), 0);
                builder2.append(str1_2);

                if (ostDays <= 7) {
                    textDaysColor = textColor_red;
                }
                else {
                    textDaysColor = textColor_grey;
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
                str3_2.setSpan(new ForegroundColorSpan(textColor_grey), 0, str3_2.length(), 0);
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
                    Date endDate = new Date();
                    endDate.setYear(endDate.getYear() + 1);

                    entity.setPolisStart(new Date());
                    entity.setPolisEnd(endDate);

                    parentAdapter.setChangedData();
                }
            });
        }

        private String getDatePeriodString(Date dateStart, Date dateEnd) {
            SimpleDateFormat simpleDate =  new SimpleDateFormat("dd.MM.yyyy");
            String resultStr = "";
            if (dateStart != null)
                resultStr = "с " + simpleDate.format(dateStart);

            if (dateEnd != null)
                resultStr += " по " + simpleDate.format(dateEnd);

            return resultStr;
        }

    }
}
