# ProgettoProgOggetti
L'aplicazione sviluppata è una applicazione REST API che ricava i dati da un dataset contenuto all'interno di un file csv e consente di effettuare alcune operazioni statistiche sui campi e operazioni di ricerca attraverso l'utilizzo di filtri. In generale, l'applicazione prende un url dove va a cercare, dopo aver fatto il parse json, il nome del file .csv da scaricare. Una volta trovato, effettua il download ed istanzia ogni record del dataset come un oggetto della classe Prodotti. L'utente potrà inoltrare delle richieste al servizio rest specificando una delle seguenti rotte ed aggiungendo eventuali filtri sui dati. Queste feature dell'applicazione vengono soddisfatte grazie all'utilizzo del framework Spring usando il modello MVC.


## Rotte 
Le rotte utilizzate nell'applicazione per fare le diverse richieste sono le seguenti:
- **GET/prodotti**: restituisce l'elenco di tutti i tipi di prodotti presenti all'interno del dataset
- **GET/metadata**: restiruisce la descrizione dei tipi di dati presenti all'interno del dataset
- **POST/prodotti**: in questa richiesta i dati vengono passati nel corpo della richiesta. Il corpo della richiesta conterrà il filtro                      che sarà applicato sui dati del dataset 
- **GET/prodotti/{filter}**: restituisce un elenco di prodotti il cui codice (product code) è uguale al filtro
- **GET/statstring**: restituisce il numero di elementi appartenenti ad un campo di tipo stringa passato come parametro
- **GET/stats**: restituisce i dati statistici generali di un campo filtrato o meno

## Filtri
I filtri implementati sono:
- **$bt**: questo filtro ci consente di filtrare il campo prezzo avendo i prezzi compresi in un intervallo di valori
- **$gt**: questo filtro ci consente di filtrare il campo prezzo avendo i prezzi maggiori di un certo valore
- **$in**: questo filtro ci sonsente di filtrare i campi di tipo stringa, desc e country, vedendo se il valore dei campi è presente                nell'array del filtro
- **$not**: questo filtro ci sonsente di filtrare i campi di tipo stringa, desc e country, vedendo se il valore dei campi non è presente                nell'array del filtro

## Formato dati 
A seguito del tipo di richiesta effettuata, il formato con cui i dati sarranno restituiti è il formato JSON che rappresenta un array di oggetti con i dati del dataset. 

> **GET**/prodotti
```json
    {
        "category": "Dairy products",
        "productCode": 1107,
        "desc": "Raw Milk",
        "marketPrice": 33.42,
        "sectorCode": "LAI",
        "briefDesc": "Raw milk - Lait cru",
        "unit": "€/100 kg",
        "country": "BE",
        "period": 201905
    },
    {
        "category": "Dairy products",
        "productCode": 1107,
        "desc": "Raw Milk",
        "marketPrice": 29.95,
        "sectorCode": "LAI",
        "briefDesc": "Raw milk - Lait cru",
        "unit": "€/100 kg",
        "country": "BG",
        "period": 201905
    }
```

> **GET**/metadata
```json
    {
        "alias": "sectorCode",
        "sourceFields": "Category",
        "type": "String"
    },
    {
        "alias": "briefDesc",
        "sourceFields": "Sector code",
        "type": "String"
    },
    {
        "alias": "unit",
        "sourceFields": "Product code",
        "type": "String"
    }
 ```
 
> **POST**/prodotti
> - corpo della richiesta contiene il filtro {"prezzo": {"$bt": [30.45,30.47]}}    
``` json
    {
        "category": "Dairy products",
        "productCode": 1107,
        "desc": "Raw Milk",
        "marketPrice": 30.46,
        "sectorCode": "LAI",
        "briefDesc": "Raw milk - Lait cru",
        "unit": "€/100 kg",
        "country": "RO",
        "period": 201905
    },
    {
        "category": "Dairy products",
        "productCode": 1107,
        "desc": "Raw Milk",
        "marketPrice": 30.47,
        "sectorCode": "LAI",
        "briefDesc": "Raw milk - Lait cru",
        "unit": "€/100 kg",
        "country": "HU",
        "period": 201708
    }
```
> - corpo della richiesta contiene il filtro {"country": {"$in": ["IT"]}}
``` json   
   {
        "category": "Dairy products",
        "productCode": 1107,
        "desc": "Raw Milk",
        "marketPrice": 39.07,
        "sectorCode": "LAI",
        "briefDesc": "Raw milk - Lait cru",
        "unit": "€/100 kg",
        "country": "IT",
        "period": 201905
    },
    {
        "category": "Dairy products",
        "productCode": 254,
        "desc": "Butter",
        "marketPrice": 384,
        "sectorCode": "LAI",
        "briefDesc": "Butter - Beurre",
        "unit": "€/100 kg",
        "country": "IT",
        "period": 201905
    }
```
> - corpo della richiesta contiene il filtro {"desc": {"$not": "Raw Milk"}}
``` json
    {
        "category": "Dairy products",
        "productCode": 254,
        "desc": "Butter",
        "marketPrice": 412.5,
        "sectorCode": "LAI",
        "briefDesc": "Butter - Beurre",
        "unit": "€/100 kg",
        "country": "BE",
        "period": 201905
    },
    {
        "category": "Dairy products",
        "productCode": 254,
        "desc": "Butter",
        "marketPrice": 434.36,
        "sectorCode": "LAI",
        "briefDesc": "Butter - Beurre",
        "unit": "€/100 kg",
        "country": "CZ",
        "period": 201905
    }
``` 

> **GET**/statstring?field=desc&filter={"prezzo": {"$bt": [20, 30]}}
```json
    {
        "element": "Whey Powder",
        "count": 43
    },
    {
        "element": "Raw Milk",
        "count": 1828
    }
```

> **GET**/prodotti/1107
```json
    {
        "category": "Dairy products",
        "productCode": 1107,
        "desc": "Raw Milk",
        "marketPrice": 33.42,
        "sectorCode": "LAI",
        "briefDesc": "Raw milk - Lait cru",
        "unit": "€/100 kg",
        "country": "BE",
        "period": 201905
    },
    {
        "category": "Dairy products",
        "productCode": 1107,
        "desc": "Raw Milk",
        "marketPrice": 29.95,
        "sectorCode": "LAI",
        "briefDesc": "Raw milk - Lait cru",
        "unit": "€/100 kg",
        "country": "BG",
        "period": 201905
    },
```

> **GET**/stats?field=prezzo&filter={"prezzo": {"$gt": 30}} 
``` json
{
    "field": "prezzo",                          
    "avg": 258.8342230711069,
    "min": 30.01,
    "max": 1023.96,
    "std": 146.52546963331525,
    "sum": 6504762.859999987,
    "count": 25131
}
```

> **GET**/stats?field=prezzo
``` json
{
    "field": "prezzo",
    "avg": 242.23794827267662,
    "min": 15.31,
    "max": 1023.96,
    "std": 153.34672732830109,
    "sum": 6556170.069999993,
    "count": 27065
}
```
## Gestione degli errori

### Numero attributi errato
Se durante il processo di importazione si riscontra che il numero di attributi di una riga del dataset non corrisponde al umero effettivo di campi del dataset viene stampato nella console la riga che ha causato l'errore e l'esecuzione del programma viene arrestata.
```java
if(data.length == meta.size()){products.add(new Prodotti(data[0],data[1],Integer.parseInt(data[2]),data[3],data[4],data[5],data[6],Integer.parseInt(data[7]),Double.parseDouble(data[8])));
}else{
      System.out.println("Numero di informazioni della riga [" + i + "] non coincide con il numero di campi del dataset");
		System.exit(1);
}
```

### Richiesta sbagliata
Se al momento di fare una richiesta l'utente digita un campo su cui non è possibile fare alcuna operazione, il server inoltrerà una http bad request con il corrispondente messaggio di errore, ad esempio digitando {"descrizione": {"$in": ["IT"]}} il campo descrizione non è supportato, allora verrà mostrato il seguente messaggio

```json
{
    "timestamp": "2019-07-17T08:17:36.764+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "operator $in not suported, only supported $bt, $gt",
    "path": "/prodotti"
}
```

## UML diagram
![UML](https://github.com/marcoamt/ProgettoProgOggetti/blob/master/UML.png)

## Use case diagram
![UML](https://github.com/marcoamt/ProgettoProgOggetti/blob/master/usecasediagram.png)

## Sequence diagram
![UML](https://github.com/marcoamt/ProgettoProgOggetti/blob/master/sequencediagram.png)

