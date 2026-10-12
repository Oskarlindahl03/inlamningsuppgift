# Planeringsmall — Projektskiss

Fyll i denna mall innan ni börjar koda. Skissen är ett första utkast, inte ett facit — det är både normalt och förväntat att klassnamn och struktur ändras när ni väl börjar implementera. Spara den ifyllda mallen som README i er första commit, tillsammans med namn på den/de som jobbar i projektet.

## Projektidé

En till två meningar: vilken domän, och vad programmet ska göra.    
  
Vi har valt domänen bank och tanken är att vi ska göra ett kontohanteringssystem där man kan göra uttag, insättningar, flytta pengar, söka efter transaktioner och köra processer varje månad för uttag och insättningar.

Det ska finnas tre konton: sparkonto, studentkonto och transaktionskonto. Tanken med detta är att det ska implementeras olika regler beroende på typ. Dessa olika regler är vad som gör polymorfism och arv betydelsefullt.


## Superklass

- Namn: Account
- Gemensamma fält: balance, accountNumber, ownerName
- Gemensamma metoder: getAccountNumber(), getBalance(), withdraw(), deposit()

## Subklasser (minst tre)

1. SavingsAccount — Har ränta och ska normalt inte tillåta obegränsade uttag. Metod som kan overridas: withdraw()
2. CheckingAccount — Kan tillåta att saldot blir negativt upp till en viss gräns. Metod som kan overridas: withdraw()
3. StudentAccount — Kan exempelvis ha avgiftsfria uttag/transaktioner. Metod som kan overridas: withdraw() eller en specifik monthlyFee()



## Interface

- Namn: Withdrawable
- Metod(er): withdraw(double amount)
- Implementeras av (minst två subklasser): Alla subklasser



## Meny

Lista minst fyra åtgärder kopplade till samlingen (t.ex. lägga till, ta bort, söka, samt en egen åtgärd som passar er domän).

1. Öppna konto
2. Stäng konto
3. Insättning
4. Uttag
5. Överföring
6. Visa transaktionshistorik
7. Kör månadsslut
8. Välj användare
0. Avsluta

## Felscenarion

Minst två konkreta situationer i just ert program som kan gå fel och som ni behöver hantera (inte generella exempel).

scenario 1: Det finns inte tillräckligt med pengar på kontot vid ett uttag

scenario 2: Vid månads slut appliceras räntan på fel konto

## Motivering (fylls i senare i veckan)

När ni kommit igång och gjort några ändringar: skriv kort varför strukturen ser ut som den gör, och om ni övervägde ett annat sätt att lösa det på. Detta behöver inte fyllas i redan i första commiten.

---