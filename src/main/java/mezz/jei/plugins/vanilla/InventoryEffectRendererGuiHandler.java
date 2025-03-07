package mezz.jei.plugins.vanilla;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.client.renderer.Rectangle2d;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

import com.google.common.collect.Ordering;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;

class InventoryEffectRendererGuiHandler implements IGuiContainerHandler<DisplayEffectsScreen> {
	/**
	 * Modeled after {@link DisplayEffectsScreen#drawActivePotionEffects()}
	 */
	@Override
	public List<Rectangle2d> getGuiExtraAreas(DisplayEffectsScreen containerScreen) {
		Collection<EffectInstance> activePotionEffects = containerScreen.getMinecraft().player.getActivePotionEffects();
		if (activePotionEffects.isEmpty()) {
			return Collections.emptyList();
		}

		List<Rectangle2d> areas = new ArrayList<>();
		int x = containerScreen.getGuiLeft() - 124;
		int y = containerScreen.getGuiTop();
		int height = 33;
		if (activePotionEffects.size() > 5) {
			height = 132 / (activePotionEffects.size() - 1);
		}
		for (EffectInstance potioneffect : Ordering.natural().sortedCopy(activePotionEffects)) {
			Effect potion = potioneffect.getPotion();
			if (potion.shouldRender(potioneffect)) {
				areas.add(new Rectangle2d(x, y, 166, 140));
				y += height;
			}
		}
		return areas;
	}
}
