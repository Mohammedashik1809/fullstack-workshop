function createShoppingCart() {
  let items = [];
  let discountPercent = 0;

  return {
    /* ================= ADD ITEM ================= */
    addItem(product) {
      const existingItem = items.find(item => item.id === product.id);

      if (existingItem) {
        existingItem.quantity += product.quantity;
      } else {
        items.push({ ...product });
      }
    },

    /* ================= REMOVE ITEM ================= */
    removeItem(id) {
      items = items.filter(item => item.id !== id);
    },

    /* ================= UPDATE QUANTITY ================= */
    updateQuantity(id, quantity) {
      const item = items.find(item => item.id === id);
      if (item && quantity > 0) {
        item.quantity = quantity;
      }
    },

    /* ================= GET ITEMS ================= */
    getItems() {
      return items.map(item => ({ ...item }));
    },

    /* ================= GET TOTAL PRICE ================= */
    getTotal() {
      const total = items.reduce((sum, item) => {
        return sum + item.price * item.quantity;
      }, 0);

      const discountAmount = (total * discountPercent) / 100;
      return +(total - discountAmount).toFixed(2);
    },

    /* ================= ITEM COUNT ================= */
    getItemCount() {
      return items.reduce((count, item) => count + item.quantity, 0);
    },

    /* ================= EMPTY CHECK ================= */
    isEmpty() {
      return items.length === 0;
    },

    /* ================= APPLY DISCOUNT ================= */
    applyDiscount(code, percent) {
      if (typeof percent === 'number' && percent > 0) {
        discountPercent = percent;
      }
    },

    /* ================= CLEAR CART ================= */
    clear() {
      items = [];
      discountPercent = 0;
    }
  };
}

const cart = createShoppingCart();

cart.addItem({ id: 1, name: 'Laptop', price: 999, quantity: 1 });
cart.addItem({ id: 2, name: 'Mouse', price: 29, quantity: 2 });
cart.addItem({ id: 1, name: 'Laptop', price: 999, quantity: 1 });

console.log(cart.getItems());
// Laptop quantity becomes 2

cart.updateQuantity(1, 3);
cart.removeItem(2);

console.log(cart.getTotal());      // 2997
console.log(cart.getItemCount());  // 3
console.log(cart.isEmpty());       // false

cart.applyDiscount('SAVE10', 10);
console.log(cart.getTotal());      // 2697.30

cart.clear();
console.log(cart.isEmpty());       // true
