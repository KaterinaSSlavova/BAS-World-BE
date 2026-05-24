package basworld.backend.business.impl.product;

import basworld.backend.business.command.CreateProductCommand;
import basworld.backend.business.command.ProductDepotCommand;
import basworld.backend.business.exception.DepotNotFound;
import basworld.backend.business.result.ProductWithDepotsResult;
import basworld.backend.business.useCase.product.CreateProductUseCase;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @AllArgsConstructor
@Transactional
public class CreateProductUseCaseImpl implements CreateProductUseCase {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TypeRepository typeRepository;
    private final DepotRepository depotRepository;
    private final ProductDepotRepository productDepotRepository;
    private final BrandRepository brandRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final SupplierRepository supplierRepository;



    public ProductWithDepotsResult createProduct(CreateProductCommand request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null!");
        }
        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("CategoryId cannot be null!");
        }
        if (request.getTypeId() == null) {
            throw new IllegalArgumentException("TypeId cannot be null!");
        }
        if (request.getBrandId() == null){
            throw new IllegalArgumentException("BrandId cannot be null!");
        }
        if (request.getVehicleTypeId() == null){
            throw new IllegalArgumentException("VehicleTypeId cannot be null!");
        }



        var category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found!"));
        var type = typeRepository.findById(request.getTypeId()).orElseThrow(() -> new IllegalArgumentException("Type Id not found!"));
        var brand = brandRepository.findById(request.getBrandId()).orElseThrow(() -> new IllegalArgumentException("Brand Id not found!"));
        var vehicleType = vehicleTypeRepository.findById(request.getVehicleTypeId()).orElseThrow(() -> new IllegalArgumentException("VehicleType Id not found!"));
        var product = new Product(request.getSku(), request.getName(), request.getDescription()
        , brand, request.getStatus(), type, category, vehicleType);
        var savedProduct = productRepository.save(product);
        List<Long> depotIdList = request.getProductDepots()
                .stream()
                .map(ProductDepotCommand::getDepotId)
                .toList();
        List<Depot> depots = depotRepository.findByMultipleIds(depotIdList);
        List<ProductDepot>  productDepots = new ArrayList<>();
        for (ProductDepotCommand command : request.getProductDepots()) {
            if (command.getSupplierId() == null) throw new IllegalArgumentException("SupplierId cannot be null!");

            Depot depot = depots.stream()
                    .filter(d -> d.getId().equals(command.getDepotId()))
                    .findFirst()
                    .orElseThrow(() -> new DepotNotFound("Depot not found"));

            var supplier = supplierRepository.findById(command.getSupplierId())
                    .orElseThrow(() -> new IllegalArgumentException("Supplier not found!"));

            var productDepot = new ProductDepot(savedProduct, depot, command.getStockQuantity(),
                    command.getCostPrice(), command.getSalePrice(), command.getStockThreshold(), supplier);

            productDepots.add(productDepot);
        }        List<ProductDepot> savedProductDepots = productDepotRepository.saveAll(productDepots);
        return new ProductWithDepotsResult(savedProduct, savedProductDepots);
    }

}
