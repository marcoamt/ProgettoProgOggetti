# ProgettoProgOggetti
L'aplicazione sviluppata è una applicazone che ricava i dati da un dataset contenuto all'interno di un file csv e consente di effettuare alcune operazioni statistiche sui campi e operazioni di ricerca attraverso l'utilizzo di filtri. 

## Rotte 
Le rotte utilizzate nell'applicazione per fare le diverse richieste sono le seguenti:
- GET/prodotti: restituisce l'elenco di tutti i tipi di prodotti presenti all'interno del dataset
- GET/metadata: restiruisce la descrizione dei tipi di dati presenti all'interno del dataset
- POST/prodotti: in questa richiesta i dati vengono passati nel corpo della richiesta. Il corpo della richiesta conterrà il filtro che     sarà applicato sui dati del dataset 

## Filtri
I filtri utilizzati nelle diverse richieste sono i seguenti:
- GET/prodotti/{filter}: restituisce un elenco di prodotti il cui codice (product code) è uguale al filtro
- GET/statstring: restituisce il numero di elementi appartenenti ad un campo di tipo stringa passato come parametro
- GET/stats: restituisce i dati statistici generali di un campo filtrato o meno

I filtri implementati sono $bt, $gt, $in e $not.

## Formato dati 
A seguito del tipo di richiesta effettuata, il formato con cui i dati sarranno restituiti è il formato JSON che rappresenta un array di oggetti con i dati del dataset. 

Richiesta GET/prodotti
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
    
Richiesta GET/metadata
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
    
Richiesta POST/prodotti - corpo della richiesta contiene il filtro {"prezzo": {"$bt": [30.45,30.47]}}    
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

Filtro GET/statstring?field=desc&filter={"prezzo": {"$bt": [20, 30]}}
    {
        "element": "Whey Powder",
        "count": 43
    },
    {
        "element": "Raw Milk",
        "count": 1828
    }
    
Filtro GET/prodotti/1107
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

Filtro GET/stats?field=prezzo&filter={"prezzo": {"$gt": 30}}   
    "field": "prezzo",                          
    "avg": 258.8342230711069,
    "min": 30.01,
    "max": 1023.96,
    "std": 146.52546963331525,
    "sum": 6504762.859999987,
    "count": 25131
}

Filtro GET/stats?field=prezzo
{
    "field": "prezzo",
    "avg": 242.23794827267662,
    "min": 15.31,
    "max": 1023.96,
    "std": 153.34672732830109,
    "sum": 6556170.069999993,
    "count": 27065
}


## UML diagrams
![UML](https://github.com/marcoamt/ProgettoProgOggetti/blob/master/POProject/src/main/java/UML.png)



## Sequence diagrams
![UML](https://github.com/marcoamt/ProgettoProgOggetti/blob/master/POProject/src/main/java/sequencediagram.png)
```mermaid
sequenceDiagram

partecipant PoProjectApplication
participant ProdottiController
participant ProdottiService
participant DownloadCSV

PoProjectApplication ->> ProdottiService: init()

ProdottiService ->>+DownloadCSV: Download file csv
DownloadCSV-->>-ProdottiService: file csv

ProdottiController->>+ProdottiService: getAllProducts()
ProdottiService-->>-ProdottiController: products

ProdottiController->>+ProdottiService: getAllMeta()
ProdottiService-->>-ProdottiController: metadata

ProdottiController->>+ProdottiService: getProductByCode(filter)
ProdottiService-->>-ProdottiController: products by code

ProdottiController->>+ProdottiService: getCountElem(filter, field)
ProdottiService-->>-ProdottiController: count list

ProdottiController->>+ProdottiService: getProductByCodeFiltro(filter)
ProdottiService-->>-ProdottiController: products by filtro

ProdottiController->>+ProdottiService: getStatsFiltro(filter, field)
ProdottiService-->>-ProdottiController: stats
