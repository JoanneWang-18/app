package com.example.afinal;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

class item{
    public String shade_name;
    public Integer lesson_image;
    public String url;
}

class uva{
    public String uva_name;
    public String uva_type;
    public String pdf;
}

public class DummyData {
    // Some dummy data for the ListView.
    // Here's a sample of shades and their meaning

    //public ArrayList<item> buffer = new ArrayList<>(9);
    public static String[] shade_name = {
            "Introduction: 16. Complexity: P, NP, NP-completeness, Reductions",
            "Introduction: 17. Complexity: Approximation Algorithms",
            "Introduction: 18. Complexity: Fixed-Parameter Algorithms",
            "NP Completeness 1 - Introduction to Hard Problems",
            "NP Completeness 2 - Listing some hard problems",
            "NP Completeness 3 - Definitions of Complexity Classes",
            "NP Completeness 4 - Satisfiability and 3SAT",
            "NP Completeness 5 - Independent Set Problem",
            "NP Completeness 6 - Independent Set Algorithms",
            "NP Completeness 7 - Clique Problem",
            "NP Completeness 8 - Vertex Cover Problem",
            "NP Completeness 9 - Set Cover Problem and Outline of Proof Technique",
            };

    public static Integer[] lesson_image ={
            R.drawable.lesson_16,
            R.drawable.lesson_17,
            R.drawable.lesson_18,
            R.drawable.lesson1,
            R.drawable.lesson2,
            R.drawable.lesson3,
            R.drawable.lesson4,
            R.drawable.lesson5,
            R.drawable.lesson6,
            R.drawable.lesson7,
            R.drawable.lesson8,
            R.drawable.lesson9
    };

    public static String[] uva_name={
            "431 - Trial of the Millennium",
            "624 - CD",
            "990 - Diving for Gold",
            "10130 - SuperSale",
            "10819 - Trouble of 13-Dots",
            "10980 - Lowest Price in Town",
            "10243 - Fire! Fire!! Fire!!!",
            "10859 - Placing Lampposts",
            "10984 - Double NP-hard",
            "11419 - SAM I AM",
            "111 - History Grading",
            "531 - Compromise",
            "775 - Hamiltonian Cycle",
            "10235 - Simply Emirp",
            "11095 - Tabriz City",
            "10066 - The Twin Towers",
            "10192 - Vacation",
            "10405 - Longest Common Subsequence",
            "10723 - Cyborg Genes",
            "10635 - Prince and Princess",
            "10949 - Kids in a Grid",
            "10723 - Cyborg Genes",
            "10661 - The Perspectographer",
            "10004 - Bicoloring",
            "10052 - Inviting Politicians"
    };
    /*
    0/1 Knapsack Problem
    Vertex Cover
    Hamilton Circuit
    Longest Common Subsequence
    Shortest Common Supersequence
    k-Vertex Coloring
    */
    public static String[] uva_type={
            "0/1 Knapsack Problem",
            "0/1 Knapsack Problem",
            "0/1 Knapsack Problem",
            "0/1 Knapsack Problem",
            "0/1 Knapsack Problem",
            "0/1 Knapsack Problem",
            "Vertex Cover",
            "Vertex Cover",
            "Vertex Cover",
            "Vertex Cover",
            "Longest Common Subsequence",
            "Longest Common Subsequence",
            "Hamilton Circuit",
            "Hamilton Circuit",
            "Vertex Cover",
            "Longest Common Subsequence",
            "Longest Common Subsequence",
            "Longest Common Subsequence",
            "Longest Common Subsequence",
            "Longest Common Subsequence",
            "Longest Common Subsequence",
            "Shortest Common Supersequence",
            "k-Vertex Coloring",
            "k-Vertex Coloring",
            "k-Vertex Coloring"
    };

    public static String[] pdf ={
            "https://onlinejudge.org/external/4/431.pdf",
            "https://onlinejudge.org/external/6/624.pdf",
            "https://onlinejudge.org/external/9/990.pdf",
            "https://onlinejudge.org/external/101/10130.pdf",
            "https://onlinejudge.org/external/108/10819.pdf",
            "https://onlinejudge.org/external/109/10980.pdf",
            "https://onlinejudge.org/external/102/10243.pdf",
            "https://onlinejudge.org/external/108/10859.pdf",
            "https://onlinejudge.org/external/109/10984.pdf",
            "https://onlinejudge.org/external/114/11419.pdf",
            "https://onlinejudge.org/external/1/111.pdf",
            "https://onlinejudge.org/external/5/531.pdf",
            "https://onlinejudge.org/external/7/775.pdf",
            "https://onlinejudge.org/external/102/10235.pdf",
            "https://onlinejudge.org/external/110/11095.pdf",
            "https://onlinejudge.org/external/100/10066.pdf",
            "https://onlinejudge.org/external/101/10192.pdf",
            "https://onlinejudge.org/external/104/10405.pdf",
            "https://onlinejudge.org/external/107/10723.pdf",
            "https://onlinejudge.org/external/106/10635.pdf",
            "https://onlinejudge.org/external/109/10949.pdf",
            "https://onlinejudge.org/external/107/10723.pdf",
            "https://onlinejudge.org/external/106/10661.pdf",
            "https://onlinejudge.org/external/100/10004.pdf",
            "https://onlinejudge.org/external/100/10052.pdf"
    };


    public static String[] url ={
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/eHZifpgyH_4\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            //"<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/MEz1J9wY2iM\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/MEz1J9wY2iM\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/4q-jmGrmxKs\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/0sQ37m3whP4\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/434lynymAmo\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/rju-ZLIyWbE\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/DY5oF7I_yz4\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/lTqTk9AQR5s\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/YUXt_OTotN8\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/mPJApHjBoro\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/vsNBb9rCwS0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
            "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/hrXe3b0C5QU\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
    };

    public static String[] getUVApdf(){
        return pdf;
    }

    public static ArrayList<item> buffer = new ArrayList<item>();

    public static ArrayList<item> getBuffer(){
        for(int i = 0;i<12;i++){
            item temp = new item();
            temp.lesson_image = lesson_image[i];
            temp.shade_name = shade_name[i];
            temp.url = url[i];
            buffer.add(temp);
        }

        return buffer;
    }


    public static ArrayList<uva> buffer1 = new ArrayList<uva>();

    public static ArrayList<uva> getBuffer1(){
        for(int i = 0;i<25;i++){
            uva temp = new uva();
            temp.uva_name = uva_name[i];
            temp.uva_type = uva_type[i];
            temp.pdf = pdf[i];
            buffer1.add(temp);
        }

        return buffer1;
    };

}
