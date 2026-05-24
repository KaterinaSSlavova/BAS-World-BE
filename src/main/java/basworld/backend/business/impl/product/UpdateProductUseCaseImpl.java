package basworld.backend.business.impl.product;

import basworld.backend.business.command.ProductDepotCommand;
import basworld.backend.business.command.UpdateProductCommand;
import basworld.backend.business.exception.DepotNotFound;
import basworld.backend.business.exception.ProductNotFound;
import basworld.backend.business.result.ProductWithDepotsResult;
import basworld.backend.business.service.StockAlertService;
import basworld.backend.business.useCase.product.UpdateProductUseCase;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.*;
import basworld.backend.domain.supplier.Supplier;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TypeRepository typeRepository;
    private final DepotRepository depotRepository;
    private final ProductDepotRepository productDepotRepository;
    private final BrandRepository brandRepository;
    private final StockAlertService alertService;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public ProductWithDepotsResult updateProduct(Long productId, UpdateProductCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Request cannot be null!");
        }
        if (command.getCategoryId() == null) {
            throw new IllegalArgumentException("CategoryId cannot be null!");
        }
        if (command.getTypeId() == null) {
            throw new IllegalArgumentException("TypeId cannot be null!");
        }
        if (command.getBrandId() == null) {
            throw new IllegalArgumentException("BrandId cannot be null!");
        }
        if (command.getVehicleTypeId() == null) {
            throw new IllegalArgumentException("VehicleTypeId cannot be null!");
        }
        if (command.getSupplierId() == null) {
            throw new IllegalArgumentException("SupplierId cannot be null!");
        }

        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new ProductNotFound("Product not found!"));
        var category = categoryRepository.findById(command.getCategoryId()).orElseThrow();
        var type = typeRepository.findById(command.getTypeId()).orElseThrow(() -> new IllegalArgumentException("Type Id not found!"));
        var brand = brandRepository.findById(command.getBrandId()).orElseThrow(() -> new IllegalArgumentException("Brand Id not found!"));
        var vehicleType = vehicleTypeRepository.findById(command.getVehicleTypeId()).orElseThrow(() -> new IllegalArgumentException("VehicleType Id not found!"));
        var supplier = supplierRepository.findById(command.getSupplierId()).orElseThrow(() -> new IllegalArgumentException("Supplier Id not found!"));

        //existing product depots
        List<ProductDepot> existingDepots =
                productDepotRepository.findByProductId(productId);
        Map<Long, ProductDepot> existingMap = existingDepots.stream()
                .collect(Collectors.toMap(pd -> pd.getDepot().getId(), pd -> pd));

        //product depot ids to remove
        Set<Long> incomingIds = command.getProductDepots().stream()
                .map(ProductDepotCommand::getDepotId)
                .collect(Collectors.toSet());
        List<ProductDepot> toDelete = existingDepots.stream()
                .filter(pd -> !incomingIds.contains(pd.getDepot().getId()))
                .toList();

        //product depots to be saved
        List<ProductDepot> result = new ArrayList<>();

        for (ProductDepotCommand cmd : command.getProductDepots()) {
            if (cmd.getSupplierId() == null) throw new IllegalArgumentException("SupplierId cannot be null!");

            var cmdSupplier = supplierRepository.findById(cmd.getSupplierId())
                    .orElseThrow(() -> new IllegalArgumentException("Supplier not found!"));

            ProductDepot productDepot = existingMap.get(cmd.getDepotId());

            if (productDepot != null) {
                // update existing product depots
                productDepot.update(cmd.isAvailable(), cmd.getStockQuantity(), cmd.getCostPrice(), cmd.getSalePrice(), cmd.getStockThreshold(), cmdSupplier);

            } else {
                Depot depot = depotRepository.findById(cmd.getDepotId())
                        .orElseThrow(() -> new DepotNotFound("Depot not found"));

                productDepot = new ProductDepot(
                        existingProduct,
                        depot,
                        cmd.isAvailable(),
                        cmd.getStockQuantity(),
                        cmd.getCostPrice(),
                        cmd.getSalePrice(),
                        cmd.getStockThreshold(),
                        cmdSupplier
                );
            }

            result.add(productDepot);
        }

        existingProduct.update(command.getName(), command.getDescription(), brand, command.getStatus(), type, category, vehicleType);

        var savedProduct = productRepository.save(existingProduct);

        productDepotRepository.deleteAll(toDelete);

        var savedProductDepots = productDepotRepository.saveAll(result);

        alertService.notifyStockChange();

        return new ProductWithDepotsResult(savedProduct, savedProductDepots);

    }
}
