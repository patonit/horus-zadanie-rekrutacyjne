import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            if (block instanceof CompositeBlock compositeBlock) {
                for (Block subBlock : compositeBlock.getBlocks()) {
                    if (subBlock.getColor().equals(color)) {
                        return Optional.of(subBlock);
                    }
                }
            } else if (block.getColor().equals(color)) {
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> result = new ArrayList<>();
        for (Block block : blocks) {
            if (block instanceof CompositeBlock compositeBlock) {
                for (Block subBlock : compositeBlock.getBlocks()) {
                    if (subBlock.getMaterial().equals(material)) {
                        result.add(subBlock);
                    }
                }
            } else if (block.getMaterial().equals(material)) {
                result.add(block);
            }
        }
        return result;
    }

    @Override
    public int count() {
        int count = 0;
        for (Block block : blocks) {
            if (block instanceof CompositeBlock compositeBlock) {
                count += compositeBlock.getBlocks().size();
            } else {
                count++;
            }
        }
        return count;
    }
}
