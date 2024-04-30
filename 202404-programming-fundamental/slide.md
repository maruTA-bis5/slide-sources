---
marp: true
paginate: true
title: Inside PrimeFaces
author: Takayuki Maruyama ([@maruTA-bis5](https://github.com/maruTA-bis5))
footer: This work by Takayuki Maruyama is licensed under [CC BY-SA 4.0 ![Creative Commons height:16px](https://mirrors.creativecommons.org/presskit/icons/cc.svg)![BY height:16px](https://mirrors.creativecommons.org/presskit/icons/by.svg)![SA height:16px](https://mirrors.creativecommons.org/presskit/icons/sa.svg)](https://creativecommons.org/licenses/by-sa/4.0/).
---

# プログラミング(再)入門

2024/05/01 社内勉強会
丸山 貴之

---
<!-- header: "プログラミング(再)入門" -->

## whoami
![h:128 gravatar](https://sessionize.com/image/17d9-200o200o2-4qpQQkNLwSrYVsEkKFPhyP.jpg)
- 丸山 貴之
- 株式会社ビーブレイクシステムズ 開発部 シニアSE
- 業務:
    - 基幹業務パッケージの開発・導入・保守
    - 新卒技術研修
    - 自社OSS(ExCellaシリーズ)メンテナ
    - 社内Mattermost管理・運営

---

## Agenda
- プログラムとは
- プログラムの構成要素
- 処理の追い方
- プログラムの実行


---

# プログラムとは

---

## プログラムとは
- 語源: `pro`(あらかじめ) + `gram`(書かれたもの) = `program`
    - 一般的にも使われる言葉 (コンサートの-、テレビ・ラジオの放送-)
- 狭義のプログラム: コンピューターに実行させたい処理を記述したもの
    - 歴史: 穴を空けた紙をコンピューターに読み込ませることで実行していた
    (パンチカード、穿孔テープ)
    - コンピューターは、プログラムに書かれたとおりにしか動かない

---

# プログラムの構成要素

---

## プログラムの構成要素
- 大まかに3つの構造の組み合わせで構成される (構造化プログラミング)
    1. 逐次実行
    2. 反復
    3. 条件分岐
- 処理に対する情報の入力、結果の出力が可能
- (Java) 実行する処理は`式`(`expression`)を組み合わせた`文`(`statement`)として書く
- (Java) 実行中は`スタック`と呼ばれる構造で処理状況を保持する
<!-- (Java)はJavaに限定した話をするが、他の言語でも同様な場合が多い -->

---

<!-- header: "プログラミング(再)入門 > プログラムの構成要素" -->
### 制御構造
1. 逐次実行(順次実行)
    - 上から順番に処理を実行する
        - メソッドが呼ばれた場合も同様  
        <!-- メソッドの処理が終わると呼んだ位置に戻る -->
2. 反復
    - 繰り返し実行する
    - `for`, `while`, `do while`
3. 条件分岐
    - 条件に応じて処理を変える
    - `if`, `if - else`, `if - else if - else`
    - `switch`
    - 三項演算子 (foo `?` bar `:` baz)
<!-- 制御構造を利用した構造化プログラミングが登場する以前は、GOTO文を使用して次の処理を指定する言語が主流だった (BASIC等) -->

---

### 入出力
- 他のプログラムとの間で情報をやり取りするには、入力・出力を使用する  
(まとめて`入出力`)
- プログラミング言語側で、どのような入出力手段でも同じようなプログラムで実現できるようになっていることが多い
    - Javaの場合
        - 入力: `java.io.InputStream`, `java.io.Reader`
        - 出力: `java.io.OutputStream`, `java.io.Writer`
        - 入出力: `java.nio.channels.Channel`

---

<!-- header: "プログラミング(再)入門 > プログラムの構成要素 > 入出力" -->
#### 入出力の例
- 標準入出力: 特別な入出力として、どのプログラムも次の3つを利用可能
    - 標準入力(standard input, stdin)
    Java: `InputStream System.in`
    - 標準出力(standard output, stdout)
    Java: `PrintStream System.out`
    - 標準エラー出力(standard error output, stderr)
    Java: `PrintStream System.err`
    - 業務では標準入出力を使う事はほぼ無い(代わりにロガー(`Logger`)を使う)
        - printデバッグ目的で一時的に使う事はある

<!-- PrintStreamはOutputStreamのサブクラス -->
<!-- 例外のprintStackTrace()は標準エラー出力に出力している -->


---

#### 入出力の例 (標準入出力以外)
- ネットワーク (Webサイト・Webアプリケーション、データベース等)
    - `java.net.Socket`, `java.net.ServerSocket`
    - `java.nio.channels.NetworkChannel`
- ファイル
    - `java.io.File`
    - `java.nio.file.Path`, `java.nio.file.Files`

---

<!-- header: "プログラミング(再)入門 > プログラムの構成要素" -->

### 式と文
- プログラムは、式を組み合わせて記述した文の集合
- 式(Expression): 評価(実行)すると次のいずれかになるもの
    - 変数
    - 値 (プリミティブでも参照型でも)
    - Nothing (void)
<!-- Java SE 21 JLS 15.1 https://docs.oracle.com/javase/specs/jls/se21/html/jls-15.html -->
- 文(Statement):
    - 代入式・インクリメント/デクリメント・メソッド実行に`;`が付いたもの <!-- ExpressionStatement -->
    - ブロック(`{...}`)
    - `if`, `for`, `while`等の制御文
<!-- Java SE 21 JLS 14.5 https://docs.oracle.com/javase/specs/jls/se21/html/jls-14.html#jls-14.5 -->

---

<!-- header: "プログラミング(再)入門 > プログラムの構成要素 > 式と文" -->
- 例1
    ```java

    boolean valid = isValid();




    if (valid) {
        System.out.println("Your input is valid!");
    }


    while (true) {
        // snip
        if (valid) {
            break;
        }
    }


    ;
    ```

---
- 例1
    ```java
    // `valid = isValid()`は式、行全体は文、`isValid()`は式
    boolean valid = isValid();

    // `valid`は式、`System.out`は式、"Your input is valid!"(文字列リテラル)は式
    // `if (valid) {...}`は文、`System.out.println(...)`は式、`System.out.println(...);`は文
    if (valid) {
        System.out.println("Your input is valid!");
    }

    // `true`(リテラル)は式、`valid`は式
    // `while (true) {...}`は文、while直後の`{...}`は文、
    // `if (valid) {...}`は文、if直後の`{...}`は文。`break;`は文
    while (true) {
        // snip
        if (valid) {
            break;
        }
    }

    // 空の文 (不要なので消しましょう)
    ;
    ```

---

<!-- _class: statement-expression -->
<style>
    section.statement-expression {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 1rem;
    }
    section.statement-expression > ul {
        grid-column-start: 1;
        grid-column-end: 3;
    }
</style>
- 例2

```java
// switch "文"
String modified;
switch (value) {
    case 1:
        modified = "A";
        break;
    case 2:
        modified = "B";
        break;
    default:
        modified = "X";
}
```
```java
// switch "式" ※Java 12+
String modified = switch (value) {
    case 1 -> "A";
    case 2 -> "B";
    default -> "X";
};
```

---

<!-- header: "プログラミング(再)入門 > プログラムの構成要素" -->

### スタック
- 後に入れた要素から順に取り出すデータ構造
    - イメージ: 本屋で平積みされている本を上から取る
- コールスタック(フレームスタック)
    - メソッド(関数)の呼び出し順・位置を格納するスタック
    - メソッド(関数)の処理に移る前: 呼び出し元の情報を格納する
    - メソッド(関数)の処理が終わるとき: スタックから取り出してその位置に戻る
    - `java.lang.StackOverflowError` = コールスタックの空き容量が無くなった
        - メソッドの中でそのメソッド自身を呼び出す(再帰)際、  
        終了条件が無い or 間違っていると発生する

---

# 処理の追い方

---

<!-- header: "プログラミング(再)入門" -->

## 処理の追い方
- 机上デバッグ
    変数の状態やメソッドの呼び出し位置を追跡しながら1行ずつ読み、それぞれの行で意図した状態となっているか確認する
- デバッガ(Eclipse, VSCode)
    机上デバッグと同じだが、変数の状態や呼び出し位置はデバッガが追跡・表示してくれる
    →ツールでできることはツールに任せて、頭の処理能力を効率よく使う

※DEMO

---

# プログラムの実行

---

<!-- header: "プログラミング(再)入門" -->

## プログラムの実行
- コンピューターが実行可能な言語は`機械語`のみ
    - CPUの種類(命令セットアーキテクチャ)毎に語彙が決められている
        - 命令セットアーキテクチャ: x64(x86-64), ARM64, PowerPC, etc...
- 人間が読み書きするのは難しいので、`プログラミング言語`を使用してプログラムを作成する
- 機械語に変換するツールとして`コンパイラー`を使用する
    - コンパイラーを使用して機械語に変換することを`コンパイル`という`

---

<!-- header: "プログラミング(再)入門 > プログラムの実行" -->

### コンパイル
コンパイラーはプログラミング言語で書かれたプログラム(ソースコード)を`解析`し、プログラム同士を`組み合わせ`て機械語に変換(`コンパイル`)する
- 解析: `字句解析`と`構文解析`の2段階がある
    1. 字句解析: 単語ごとの意味を解釈する
        - 例: 字句解析.odp :eyes:
    2. 構文解析: 文全体の意味を解析する
- 字句解析でエラーが発生すると、構文解析は行われない
    <!-- 識別子が連続する、等 -->
- 組み合わせ(`リンク`)はコンパイル時と実行時のいずれかのタイミングで行われる (静的リンクと動的リンク)
    - 静的リンク: C/C++, Go
    - 動的リンク: Java, C/C++, C#, スクリプト言語(PHP, Ruby, Python, etc…)

---

### Javaのコンパイラー (`javac`)
- Javaソースファイル(`*.java`)からクラスファイル(`*.class`)を生成する
    - クラスファイルは __機械語ではなく__、Java仮想マシン(JavaVM, JVM)が解釈可能なコード
- プログラムを実行したときにJVMがクラスファイルを解釈し、機械語に変換して実行する
    - CやGoといったプログラミング言語では、コンパイルしてCPUアーキテクチャ毎の実行ファイルを作成する  
    (例: x86-64用の実行ファイルはARM64のCPUでは動かせない)
    - CPUアーキテクチャに関わらず、同じクラスファイルを実行可能にする為の仕組み (Write Once, Run Anywhere)

--- 

<!-- header: プログラミング(再)入門 -->

## まとめ

- プログラム: コンピューターに実行させたい処理を記述したもの
- 構成要素:
    - 逐次実行、反復、条件分岐の3つの構造で構成される
    - 処理に対する情報の入力と結果の出力が可能
    - 式と文:
        - 式: 変数、値、Nothing (void) など
        - 文: 代入式、ブロック、制御文など
    - コールスタック(フレームスタック)はメソッド呼び出しの順序を格納
- 実行:
    - プログラミング言語でプログラムを作成し、コンパイラーで機械語に変換
    - JVMはJavaのクラスファイルを解釈して実行

---

# Appendix

---

<!-- header: プログラミング(再)入門 > Appendix -->

## Appendix - Stream APIの考え方
- Streamは生成元(ソース)、中間操作、終端処理で構成される
    - ソース: `list.stream()`, `Stream.of`, `IntStream.range`, etc...
        - 複数の要素を持つ何か
    - 中間操作: 要素1つ1つに対する操作
    - 終端操作: 要素を最終的にどうするか
- 終端操作が実行されるタイミングまで、中間操作の実行は遅延される
<!-- Streamに対してどのような操作をするのか全て決まるまで実行されない -->

---

## Appendix - Stream APIの考え方
例(拡張for文 vs Stream API)
```java
List<Integer> list; // 1~100が格納されているとする
List<String> even = new ArrayList<>();
for (Integer item : list) {
    if (item % 2 == 0) {
        String evenStr = String.valueOf(item);
        even.add(evenStr);
    }
}
```
```java
List<Integer> list; // 1~100が格納されているとする
List<String> even = list.stream() // Streamを生成
    .filter(i -> i % 2 == 0) // 偶数のみにする
    .map(String::valueOf) // Stringに変換
    .toList(); // Listとして集約
```
