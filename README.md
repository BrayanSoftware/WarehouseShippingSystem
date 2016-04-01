Warehouse Shipping System: Assignment

1.	Online retailers such as Amazon use multiple shipping companies (such as Royal Mail, DHL) depending on customer requirements (such as speed and price). Separate the details of the package to be shipped from the shipping service itself via a defined interface. The system should handle the dispatch of packages of various weights via different shipping companies. The shipping cost of a package is defined by its weight. It should be possible to check the delivery cost of a particular package, and to send a package to the shipping company selected by the user based on the shipping cost and time. Use the tables in the appendix to calculate delivery costs for the two shipping companies.

Appendix: Tables of Delivery Costs

Royal Mail 

Package weight     Cost                          Shipping time 

Up to 500g         £ 2.50                          2 days 

Up to 1kg          £ 5.00                          2 days 

1kg                £ 5.00 per kg or part thereof   4 days

DHL 

Package weight   Cost                             Shipping time

Any               £ 10.00 + £10 x (weight in kg)   1 day

2.	The warehouse has three packaging options with different volume capacities and weights: (a) An envelope which weighs 150g and can be used for items with the size of up to 30x22x7 cm. (b) A small box which weighs 500g for items up to 30x25x25 cm. (c) A big box which weighs 1.5kg for items up to 60x30x30 cm. Extend the functionality of the package class to deal with the available packaging options so that items can be ordered without having to specify the total package weight. Assume that the warehouse does not sell any items that are disproportionally heavy for their size so that packaging can be chosen purely based on the size of the items.

3.	The management of the retail company require statistics on the use of each shipping company, such as the number of parcels sent, the total weight and the total price. Provide the ability to report on this information.

4.	Another shipping company, BulkPack, sends any size parcel for £25, arriving in 10 days. Add this shipping company to your system and show that the existing methods such as parcel dispatch and reporting work with this company.

5.	Extend the system so the dispatch team can print out a list of parcels to be sent with each shipping company, including the delivery address and postage cost.
