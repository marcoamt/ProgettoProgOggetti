# ProgettoProgOggetti
Repository per lo sviluppo del proggetto di Programmazione ad Oggetti

modalità implementative delle statistiche e dei filtri
o elencare esempi di test che saranno utilizzati per la verifica delle funzionalità
## UML diagrams
![UML](https://github.com/marcoamt/ProgettoProgOggetti/blob/master/POProject/src/main/java/UML.png)



## Sequence diagrams
![UML](https://github.com/marcoamt/ProgettoProgOggetti/blob/master/POProject/src/main/java/sequencediagram.png)
```mermaid
sequenceDiagram

participant ProdottiController
participant ProdottiService
participant DownloadCSV
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
