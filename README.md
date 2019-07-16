# ProgettoProgOggetti
L'aplicazione sviluppata è una applicazone che ricava i dati da un dataset contenuto all'interno di un file csv e consente di effettuare alcune operazioni statistiche sui campi e operazioni di ricerca attraverso l'utilizzo di filtri. 

## Rotte 
Le rotte utilizzate nell'applicazione per fare le diverse richieste sono le seguenti:
- GET/prodotti-->jrgijpigjwpijg
- GET/metadata->kofwfkpr
- GET/statstring: uhdiuhwefuiwe
- GET/stats: jnfqurjk

##Filtri
I filtri utilizzati nelle diverse richieste sono i seguenti:
- GET/prodotti/{filter}
- POST/prodotti
I filtri implementati sono $bt, $gt, $in e $not.

modalità implementative delle statistiche e dei filtri
o elencare esempi di test che saranno utilizzati per la verifica delle funzionalità
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
