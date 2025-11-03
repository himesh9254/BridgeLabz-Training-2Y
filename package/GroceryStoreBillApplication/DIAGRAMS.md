# Grocery Store Bill Generation Application - UML Diagrams

## 1. Class Diagram

### Description
The class diagram models the system where a customer buys products, and the bill is generated. Demonstrates composition relationship.

### Classes and Relationships

```
┌─────────────────────┐
│     Customer        │
├─────────────────────┤
│ - customerId: String│
│ - name: String      │
│ - phone: String     │
│ - cart: List        │
├─────────────────────┤
│ + addToCart()       │
│ + removeFromCart()  │
│ + checkout()        │
│ + displayInfo()     │
└─────────────────────┘
           │
           │ Composition (1..*)
           │ (Customer OWNS cart items)
           ◆
┌─────────────────────┐
│      Product        │
├─────────────────────┤
│ - productName: String│
│ - quantity: double  │
│ - pricePerUnit: double│
│ - category: String  │
├─────────────────────┤
│ + getTotalPrice()   │
│ + displayInfo()     │
└─────────────────────┘
           │
           │ Uses
           ▼
┌─────────────────────┐
│  BillGenerator      │
├─────────────────────┤
│ - taxRate: double   │
│ - discount: double  │
├─────────────────────┤
│ + calculateSubtotal()│
│ + calculateTax()    │
│ + calculateTotal()  │
│ + generateBill()    │
│ + printReceipt()    │
└─────────────────────┘
```

### Key Points
- **Composition**: Customer OWNS Product items in cart (strong relationship - products in cart don't exist without customer's purchase)
- **Association**: BillGenerator uses Customer and Product data
- **Business Logic**: Tax calculation, discounts, receipt generation

---

## 2. Object Diagram

### Scenario: Customer Alice's Shopping Cart at Checkout

```
┌──────────────────────────────┐
│  alice:Customer              │
├──────────────────────────────┤
│ customerId = "C001"          │
│ name = "Alice"               │
│ phone = "123-456-7890"       │
└──────────────────────────────┘
           │
           │ owns cart
           ◆
┌──────────────────────────────┐
│  apples:Product              │
├──────────────────────────────┤
│ productName = "Apples"       │
│ quantity = 2.0               │
│ pricePerUnit = 3.0           │
│ category = "Fruits"          │
│ totalPrice = 6.0             │
└──────────────────────────────┘

┌──────────────────────────────┐
│  milk:Product                │
├──────────────────────────────┤
│ productName = "Milk"         │
│ quantity = 1.0               │
│ pricePerUnit = 2.0           │
│ category = "Dairy"           │
│ totalPrice = 2.0             │
└──────────────────────────────┘

┌──────────────────────────────┐
│  bread:Product               │
├──────────────────────────────┤
│ productName = "Bread"        │
│ quantity = 2.0               │
│ pricePerUnit = 1.5           │
│ category = "Bakery"          │
│ totalPrice = 3.0             │
└──────────────────────────────┘

┌──────────────────────────────┐
│  billGen:BillGenerator       │
├──────────────────────────────┤
│ taxRate = 0.08               │
│ discount = 0.0               │
│ (generates bill for alice)   │
└──────────────────────────────┘
```

### Snapshot Details
- **Customer**: Alice (ID: C001)
- **Cart Items**: 
  - Apples: 2 kg @ $3/kg = $6.00
  - Milk: 1 liter @ $2/liter = $2.00
  - Bread: 2 loaves @ $1.5/loaf = $3.00
- **Subtotal**: $11.00
- **Tax (8%)**: $0.88
- **Total**: $11.88

---

## 3. Sequence Diagram

### Scenario: Customer Checkout and Bill Generation

```
Customer        BillGenerator      Product
  │                    │              │
  │ checkout()         │              │
  │───────────────────>│              │
  │                    │              │
  │                    │ getCart()    │
  │                    │─────────────>│
  │                    │              │
  │                    │<─────────────│
  │                    │ [cart items] │
  │                    │              │
  │                    │ Loop [for each product]
  │                    │ ┌────────────────────┐
  │                    │ │ getTotalPrice()    │
  │                    │ │───────────────────>│
  │                    │ │                    │
  │                    │ │<───────────────────│
  │                    │ │ [price]            │
  │                    │ └────────────────────┘
  │                    │                    │
  │                    │ calculateSubtotal() │
  │                    │ [internal calc]     │
  │                    │                    │
  │                    │ calculateTax()      │
  │                    │ [internal calc]     │
  │                    │                    │
  │                    │ calculateTotal()    │
  │                    │ [internal calc]     │
  │                    │                    │
  │                    │ printReceipt()      │
  │                    │ [display]           │
  │                    │                    │
  │<───────────────────│                    │
  │ [bill details]     │                    │
  │                    │                    │
```

### Interaction Steps
1. Customer initiates checkout process
2. BillGenerator retrieves cart items from Customer
3. For each product in cart:
   - Get product details (name, quantity, price)
   - Calculate individual item total
4. BillGenerator calculates subtotal (sum of all items)
5. BillGenerator calculates tax (subtotal × tax rate)
6. BillGenerator applies any discounts
7. BillGenerator calculates final total
8. BillGenerator prints receipt
9. Return bill details to Customer

### Message Types
- **Solid arrows** (→): Synchronous method calls
- **Dashed arrows** (⇢): Return values
- **Activation bars**: Show object processing time
- **Loop frame**: Iteration through cart products
- **Alt frame**: Conditional logic (discounts, special offers)

---

## Design Patterns and Principles Used

1. **Composition**: Strong ownership - cart items belong to customer's purchase
2. **Single Responsibility**: 
   - Customer: Manages customer data and cart
   - Product: Represents product information
   - BillGenerator: Handles all billing logic
3. **Encapsulation**: Private attributes with controlled access
4. **Separation of Concerns**: Business logic separated from data models
5. **Open/Closed Principle**: Easy to extend with new product types or tax rules

---

## Key Differences from School Results Application

| Aspect | School Results | Grocery Store |
|--------|---------------|---------------|
| Relationship Type | Aggregation (loose) | Composition (tight) |
| Object Lifecycle | Subjects exist independently | Cart items tied to purchase |
| Primary Calculation | Grade calculation | Price calculation with tax |
| Data Persistence | Student records persist | Cart cleared after checkout |
| Business Domain | Education | Retail/Commerce |
