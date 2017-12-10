package upmc.pcg.ui;

import java.util.HashMap;

/**
 * @author Laura
 */

public class PrintCardUI {

    public static void printCard(HashMap<String, String> c){
        String card="";
        card +=limit_area();
        
        switch(c.get("card_type")){
            case "ENERGY":
                card +=add_line(c.get("card_type"), c.get("energy_type"));
                card +=add_ascii(c.get("energy_type"));
                break;
            case "POKEMON":
                card +=add_line(c.get("card_type"), c.get("energy_type"));
                card += add_line(c.get("name"), "HP "+c.get("HP"));
                card += add_line("","");
                for(int i=1; i<=2; i++){
                    card += add_line(c.get("attack"+i+"_name"), c.get("attack"+i+"_strenght"));
                    card += add_line(c.get("attack"+i+"_energy_needed"), "");
                }
                card += add_line("","");
                card += add_line("Weackness", "Resistance");
                card += add_line(c.get("weakness"), c.get("resistance"));
                card += add_line("","");
                card += add_line("Retreat cost :", c.get("retreat_cost")); 
                card += add_line("","");
                card += add_block(c.get("description"));
                break;
            case "TRAINER":
                card +=add_line(c.get("card_type"), "");
                card += add_line("","");
                card += add_block(c.get("description"));
        }
        

        card +=limit_area();
        
        System.out.println(card);
    }
    private static String limit_area(){
        return "-----------------------------------\n";
    }
    private static String add_line(String col1, String col2){
        int length_col1=col1.length();
        int length_col2=col2.length();
        int space_between=31-length_col1-length_col2;
        String line="";
        
        line+="| ";
        line+=col1;
        for(int i=0; i<=space_between; i++){
            line+=" ";
        }
        line += col2;
        line += " |\n";
        
        return line;
    }
    private static String add_block(String text){

        String block="";

        while(text.length()> 31){
                int cesura = 31;
                while(text.charAt(cesura)!=' ' && cesura >0){

                    cesura--;
                }
                
                if(cesura==0) cesura=31;
                block+=add_line(text.substring(0, cesura),"");
                text=text.substring(cesura+1);
        }
        block +=add_line(text, "");

        return block;
    }

    private static String add_ascii(String type) {
        System.out.println(type);
        String ascii="";
        String source;
        switch(type){
            case "grass" :
                source="       _ _\n" +"      (_\\_)\n" +"     (__<_{}\n" +"      (_/_)\n" +"     |\\ |   \n" +"      \\\\| /|\n" +"       \\|//\n" +"        |/\n" +"   ,.,.,|.,.,.\n" +"jgs^`^`^`^`^`^";
                break;
            case "fire" :
                source="              (\n" +"             ( ,)\n" +"            ). ( )\n" +"           (, )' (.\n" +"          \\WWWWWWWW/\n" +"           '--..--'\n" +"              }{\n" +"              {}";
                break;
            case "water" :
                source="         ,\n" +"         )\\\n" +"        /  \\\n" +"       '  # '\n" +"       ',  ,'\n" +"         `'";
                break;
            case "lightning" :
                source="     _, .--.\n" +"    (  / (  '-.\n" +"jgs .-=-.    ) -.\n" +"   /   (  .' .   \\\n" +"   \\ ( ' ,_) ) \\_/\n" +"    (_ , /\\  ,_/\n" +"      '--\\ `\\--`\n" +"         _\\ _\\\n" +"         `\\ \\\n" +"          _\\_\\\n" +"          `\\\\\n" +"            \\\\\n" +"        -.'.`\\.'.-";
                break;
            case "psychic" :
                source="       _---~~(~~-_.\n" +"     _{        )   )\n" +"   ,   ) -~~- ( ,-' )_\n" +"  (  `-,_..`., )-- '_,)\n" +" ( ` _)  (  -~( -_ `,  }\n" +" (_-  _  ~_-~~~~`,  ,' )\n" +"   `~ -^(    __;-,((()))\n" +"         ~~~~ {_ -_(())\n" +"                `\\  }\n" +"                  { }";
                break;
            case "fighting" :
                source="       ,--.--._\n" +"------\" _, \\___)\n" +"        / _/____)\n" +"        \\//(____)\n" +"------\\     (__)\n" +"       `-----\"\n" +" \n" +"JW";
                break;
            case "darkness" :
                source="     --_-_-_-_---\n" +"        -_-_-_\n" +"         -_-_-\n" +"          -__-\n" +"         _-_\n" +"        _-\n" +"        -_\n" +"         _-_";
                break;
            case "metal" :
                source="       /.,------------,.\\ \n" +"      ///  .=^^^^^^^\\__|\\\\\n" +"      \\\\\\   `------.   .//\n" +"       `\\\\`--...._  `;//' \n" +"         `\\\\.-,___;.//'\n" +"           `\\\\-..-//'\n" +"      jgs    `\\\\//'     \n" +"               \"\" ";
                break;
            case "fairy" :
                source="   _   vvvvvvvvv   _\n" +"  ( `-._\\...../_.-' )\n" +"   \\   ((('_')))   /\n" +"    )   ))) (((   (\n" +"   (   ((( v )))   )\n" +"    )`--' )X( `-._(\n" +"   /   _./   \\._   \\\n" +"  /  .' /     \\ `.  \\\n" +" (__/  /       \\  \\__)\n" +"      /         \\\n" +"jgs  /           \\\n" +"    WwWwWwWwWwWwWwW";
                break;
            case "dragon" :
                source="         /\\_/\\\n" +"     /\\  |6 6|  /\\\n" +"    /  \\ \\<\">/ /  \\\n" +"   / ,__`~)-(~___, \\\n" +"  /.',-'`/_/`'-,  '.\\\n" +"   ,'    \\_\\    ',\n" +"  :       \\|\\     ;\n" +"   ',     /|/    ,'\n" +"ldb  '-,__\\W\\_,-))\n" +"               ((\n" +"                )";
                break;
            case "colorless" :
                source="   ,\n" +"__/ \\__\n" +"\\     /\n" +"/_   _\\\n" +"  \\ /\n" +"   '";
                break;
            default :
                source="";
        }
        while(source.indexOf("\n")!=-1){
                    ascii += add_line(source.substring(0, source.indexOf("\n")), "");
                    source=source.substring(source.indexOf("\n")+1);
                }
        return ascii;
    }
}
