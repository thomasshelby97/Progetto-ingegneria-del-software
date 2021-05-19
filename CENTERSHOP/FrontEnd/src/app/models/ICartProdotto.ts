export  class ICartProdotto{
    id: String;
    descrizione: String;
    prezzo:number;
    quantita : number;



    constructor(id: String,desc : String, prezzo :number, quantita: number) {
      this.id = id;
      this.descrizione = desc;
      this.prezzo = prezzo;
      this.quantita = quantita;
    }
  }