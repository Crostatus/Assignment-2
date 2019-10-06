# Assignments
Esercizi di programmazione assegnati settimanalmente dal corso "Laboratorio di Reti" che sto frequentando.

assignment#1- Simulazione di un ufficio postale.


Simulare il flusso di clienti in un ufficio postale che ha 4 sportelli. Nell'ufficio esiste:

●un'ampia sala d'attesa in cui ogni persona può entrare liberamente. Quando entra, ogni persona prende il numero dalla numeratrice e aspetta il proprio turno in questa sala.
●una seconda sala, meno ampia, posta davanti agli sportelli, in cui si puòentrare solo  a gruppi di k persone•Una persona si mette quindi prima in coda nella prima sala, poi passa nella seconda sala.
•Ogni  persona  impiega  un  tempo  differente  per  la  propria  operazione  allo sportello. Una volta terminata l'operazione, la persona esce dall'ufficio

Scrivere un programma in cui:
●l'ufficio viene modellato come una classe JAVA, in cui viene attivato un ThreadPooldi dimensione uguale al numero degli sportelli
●la coda delle persone presenti nella sala d'attesa è gestita esplicitamente dal programma●la seconda coda (davanti agli sportelli) è quella gestita implicitamente dal ThreadPool
●ogni  persona  viene  modellata  come  un  task,  un  task  che  deve  essere assegnato ad uno dei thread associati agli sportelli
●si preveda di far entrare tutte le persone nell'ufficio postale, all'inizio del programmaù
•Facoltativo: prevedere  il caso di un flusso continuo di clienti e la possibilitàche l'operatore  chiuda lo sportello stesso dopo che in un certo intervallo di tempo non si presentano clienti al suo sportello.
