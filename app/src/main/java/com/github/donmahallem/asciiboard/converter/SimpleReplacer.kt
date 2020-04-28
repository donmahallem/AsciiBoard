package com.github.donmahallem.asciiboard.converter

class SimpleReplacer(val mapping:HashMap<String,String>,val ignoreCase:Boolean=false):BaseConverter {

    public override fun convert(inp:String):String{
        var converted:String=inp;
        this.mapping.keys.forEach {
            val keyValue= this.mapping[it];
            if(keyValue!= null)
                converted=converted.replace(it,keyValue,this.ignoreCase);
        }
        return converted;
    }
}