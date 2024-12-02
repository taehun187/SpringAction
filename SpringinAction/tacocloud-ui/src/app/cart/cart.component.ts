import { Component, OnInit, Injectable } from '@angular/core';
import { CartService } from './cart-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
//import { Taco } from './taco.model'; 

@Component({
  selector: 'taco-cart',
  templateUrl: 'cart.component.html',
  styleUrls: ['./cart.component.css']
})

@Injectable()
export class CartComponent implements OnInit {

  model = {
    deliveryName: '',
    deliveryStreet: '',
    deliveryState: '',
    deliveryZip: '',
    ccNumber: '',
    ccExpiration: '',
    ccCVV: '',
	deliveryCity: '', // 이 줄을 추가합니다.
    tacos: [] as any[]
  };

  constructor(private cart: CartService, private httpClient: HttpClient) {
    this.cart = cart;
  }

  ngOnInit() {}

  get cartItems() {
    return this.cart.getItemsInCart();
  }

  get cartTotal() {
    return this.cart.getCartTotal();
  }

  onSubmit() {
    // this.model.tacos = this.cart.getItemsInCart();
    this.cart.getItemsInCart().forEach(cartItem => {
      this.model.tacos.push(cartItem.taco);
    });

    this.httpClient.post(
        'http://localhost:8080/orders',
        this.model, {
            headers: new HttpHeaders().set('Content-type', 'application/json')
                    .set('Accept', 'application/json'),
        }).subscribe(r => this.cart.emptyCart());

    // TODO: Do something after this...navigate to a thank you page or something
  }

}
