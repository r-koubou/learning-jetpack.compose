package compose


// リテラルで書いた場合、数値のみでは単位が分かりにくいので、拡張関数を定義
val Int.percent : Int
        get() = this

val Float.percent : Float
    get() = this
