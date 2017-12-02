# event-miner
Simple Event Miner In Java ;  which can give you the transitions happened in transaction, given the list of events in a transaction.

This project is intented to generate, releations between events, the sequence in which they took place based on the, event logs available.

The Logical terms related to this library are

1. Transaction - It should have a primary key to establish itseld in a set of event logs.
               - A transaction contains events, it may be of same transaction or different.
2. Event - An event has primary key, event identifier and a sorter property. 
         - An event of a transaction will be identified through the key element.
         - An event list can be sorted by a sorter.
         - Relation/Sequence/Links between these events is exposed through the identifier and the sorted order
