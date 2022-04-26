<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>


<div id="addProductPopup" class="modal fade" tabindex="-1" role="dialog">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h7 class="modal-title">Add product to Shopping cart</h7>
              </div>
              <div class="modal-body row">
                <div class="col-xs-12 col-sm-6">
                  <div class="thumbnail">
                    <img class="product-image" src="data:image/gif;base64,R0lGOD1hAQABAAD/ACwAAAAAAQABAAACADs=" alt="Product image">
                  </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                  <h8 class="name">Name</h8>
                   <div class="list-group hidden-xs" style="margin-top: 10px;margin-bottom: 17px">
                    <span class="list-group-item"><small>Category: </small> <span class="category">?</span></span>
                    <span class="list-group-item"><small>Producer: </small> <span class="producer">?</span></span>
                  </div>
                  <div class="list-group">
                    <span class="list-group-item"><small>Price:</small> <span class="price">0</span></span>

                    <span class="list-group-item"><small>Amount:</small> 
                    <input type="number" class="count" value="1" min="1" max="10"></span>

                    <span class="list-group-item"><small>Cost:</small> <span class="cost">0</span></span>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button style="margin-left:10px" type="button" class="btn btn-outline pull-right" data-dismiss="modal">Close</button>
                
                <button id="addToCard" type="button" class="btn btn-outline">Add to Cart</button>
              </div>
            </div>
          </div>
        </div>