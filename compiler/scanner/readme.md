# Fase 1 Scanner

Scanner que logra identificar los tokens del lenguaje Decaf.

## Diagrama de flujo del analizador léxico

![Imgur](https://imgur.com/aCyg6U6.jpg)

## Procesos

* JFlex: se descarga la librería JFlex para poder ejecutarla en el IDE de Java ☕️
* lexer.jflex: en este archivo se configuraron todas las expresiones regulares de los tokens, junto con las palabras reservadas para el compilador. 
* tokens.java: en este se encuentra el listado de los tokens determinados en el archivo anterior.
* lexer.java: este se generó basado en el archivo Principal.java que se ejecutó para que la librería JFlex generara el código para el analizador léxico.
* Lexical analysis: proceso de identificación de caracteres basado en los tokens estipulados.

### Referencias

[Documento del lenguaje Decaf](https://acrobat.adobe.com/link/review?uri=urn:aaid:scds:US:31262356-57ea-365b-b64e-a69a89f63585)
[JFlex](https://www.jflex.de/download.html)

