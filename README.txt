# MP
Progetto svolto per il corso Metodologie di Programmazione

## Descrizione delle funzionalità del sistema
L’idea con la quale è stato creato questo progetto è quella di fornire un sistema software in grado di gestire alcuni mezzi di
trasporto, con relativi hub, offerti dallo Stato italiano. I mezzi di trasporto pubblici considerati sono gli aerei, i bus ed i treni. I
bus ed i treni presi in considerazione sono utilizzati per trasportare le persone verso un’aeroporto, quindi viene assunto che
un aeroporto sia il punto di arrivo di bus o di treni. Le macchine e le motociclette rappresentano i veicoli delle persone che
utilizzeranno i mezzi pubblici. Questi saranno parcheggiati nei garage.
I mezzi pubblici sono rappresentati dalle classi AirPlaneComposite, BusComposite e TrainComposite. I mezzi delle persone
sono rappresentati dalle classi CarLeaf e MotorBikeLeaf.
Gli Hub sono rappresentati dalle classi AirPortComposite, StationComposite e GarageComposite che a loro volta, come
verrà descritto in seguito, sono composizioni di oggetti.
Il sistema prevede di avere all’apice un aeroporto, il quale può contenere più gate, stazioni, piste di atterraggio e garage.
Aeroporti e stazioni possono avere, rispettivamente, più gate, stazioni, piste di atterraggio, garage o binari.
Binari, piste di atterraggio, gates possono essere occupati al più un veicolo.
Gli schermi, contenuti all’interno di un Hub, sono replicati all’interno della struttura che contiene l’Hub. Uno schermo ha la
funzione di far visualizzare il veicolo che staziona nell’Hub in cui è posizionato.

## Motivazioni della scelta dei Pattern utilizzati e dell’implementazione del sistema
I pattern utilizzati in questo progetto sono i seguenti: Adapter, Composite, Iterator, Observer e Visitor.
### Adapter
Il pattern Adapter è stato utilizzato in quanto, nell’implementazione del pattern Visitor, alcuni dei metodi ereditati non
avrebbero avuto un utilizzo pratico.
La classe PersonVisitorAdapter fornisce un implementazione “vuota” dei metodi del Visitor che presentano come
argomento un oggetto (Composto o Foglia) che non contiene direttamente o indirettamente persone. I veicoli che
contengono persone sono gli aeroplani, i bus ed i treni e indirettamente tutti gli Hub dove i questi veicoli stazionano.
La classe PersonVisitorAdapter è implementata all’interno del package mp.progetto.pattern.adapter e viene estesa da
PersonVisitor.
Composite
Il pattern Composite è stato utilizzato in quanto il suo intento permette di trattare strutture ad albero come Hub che
contengono veicoli, Hub che contengono Hub e veicoli che contengono persone.
