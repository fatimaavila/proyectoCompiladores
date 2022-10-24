# Symbol table desde AST

1. Se toma el AST o el JSON en cualquiera de los dos casos, y lee junto con las librerías adjuntadas. 
2. Al leer el archivo, empieza tomando cada bloque como un scope, y va contando desde ahí, a cada variable se le da un identifier. Este no es por cada scope si no en todo el archivo que lee.
3. Chequea la unicidad de cada variable, en una manera como stack. Va recorriendo y buscando si la variable YA fue ingresada, al encontrarla duplicada para el programa. 

<img width="363" alt="image (5)" src="https://user-images.githubusercontent.com/69205813/197431808-7a4ecd22-16c0-465b-b1c8-bddfce012fe4.png">

<img width="538" alt="image" src="https://user-images.githubusercontent.com/69205813/197432652-338b16c6-0b51-4266-a00e-b519c95596e8.png">
Representación gráfica de como chequea la unicidad.
